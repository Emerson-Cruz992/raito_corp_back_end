package com.projetoIntegrador.RaitoCorp.vendas.service;

import com.projetoIntegrador.RaitoCorp.vendas.model.*;
import com.projetoIntegrador.RaitoCorp.vendas.repository.*;
import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import com.projetoIntegrador.RaitoCorp.estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final EstoqueRepository estoqueRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository, EstoqueRepository estoqueRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
        this.estoqueRepository = estoqueRepository;
    }

    // Criar ou buscar carrinho existente
    public Carrinho obterOuCriarCarrinho(UUID idCliente) {
        return carrinhoRepository.findByIdCliente(idCliente)
                .orElseGet(() -> {
                    Carrinho novo = new Carrinho();
                    novo.setIdCliente(idCliente);
                    return carrinhoRepository.save(novo);
                });
    }

    public List<ItemCarrinho> listarItens(UUID idCarrinho) {
        return itemCarrinhoRepository.findByIdCarrinho(idCarrinho);
    }

    @Transactional
    public ItemCarrinho adicionarItem(UUID idCarrinho, UUID idProduto, Integer quantidade, BigDecimal preco) {
        System.out.println("=== ADICIONAR ITEM AO CARRINHO (BACKEND) ===");
        System.out.println("Carrinho: " + idCarrinho + ", Produto: " + idProduto + ", Quantidade recebida: " + quantidade);

        // Validar estoque disponível
        Integer estoqueDisponivel = estoqueRepository.findByIdProduto(idProduto)
                .map(estoque -> estoque.getQuantidade())
                .orElse(0);

        ItemCarrinho.PK pk = new ItemCarrinho.PK(idCarrinho, idProduto);

        ItemCarrinho item = itemCarrinhoRepository.findById(pk)
                .orElseGet(() -> {
                    ItemCarrinho novo = new ItemCarrinho();
                    novo.setIdCarrinho(idCarrinho);
                    novo.setIdProduto(idProduto);
                    novo.setQuantidade(0);
                    novo.setPrecoUnitario(preco);
                    return novo;
                });

        System.out.println("Quantidade atual no carrinho: " + item.getQuantidade());

        // Verificar se a quantidade solicitada excede o estoque
        Integer novaQuantidade = item.getQuantidade() + quantidade;
        System.out.println("Nova quantidade (atual + recebida): " + novaQuantidade);

        if (novaQuantidade > estoqueDisponivel) {
            throw new RuntimeException("Estoque insuficiente. Disponível: " + estoqueDisponivel + " unidades. Você já tem " + item.getQuantidade() + " no carrinho.");
        }

        item.setQuantidade(novaQuantidade);
        item.setPrecoUnitario(preco);

        return itemCarrinhoRepository.save(item);
    }

    @Transactional
    public void removerItem(UUID idCarrinho, UUID idProduto) {
        itemCarrinhoRepository.deleteById(new ItemCarrinho.PK(idCarrinho, idProduto));
    }

    @Transactional
    public void limparCarrinho(UUID idCarrinho) {
        List<ItemCarrinho> itens = itemCarrinhoRepository.findByIdCarrinho(idCarrinho);
        itemCarrinhoRepository.deleteAll(itens);
    }

    public BigDecimal calcularTotal(UUID idCarrinho) {
        return itemCarrinhoRepository.findByIdCarrinho(idCarrinho).stream()
                .map(i -> i.getPrecoUnitario().multiply(BigDecimal.valueOf(i.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
