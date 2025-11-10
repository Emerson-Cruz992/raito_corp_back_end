package com.projetoIntegrador.RaitoCorp.vendas.service;

import com.projetoIntegrador.RaitoCorp.vendas.model.*;
import com.projetoIntegrador.RaitoCorp.vendas.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, ItemCarrinhoRepository itemCarrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.itemCarrinhoRepository = itemCarrinhoRepository;
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

        item.setQuantidade(item.getQuantidade() + quantidade);
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
