package com.projetoIntegrador.RaitoCorp.estoque.service;

import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import com.projetoIntegrador.RaitoCorp.estoque.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    public List<Estoque> listarTodos() {
        return estoqueRepository.findAll();
    }

    public Estoque buscarPorProduto(UUID idProduto) {
        return estoqueRepository.findByIdProduto(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));
    }

    @Transactional
    public Estoque atualizarQuantidade(UUID idProduto, int novaQuantidade) {
        // Upsert: cria se não existe, atualiza se existe
        Estoque estoque = estoqueRepository.findByIdProduto(idProduto)
                .orElseGet(() -> {
                    Estoque novo = new Estoque();
                    novo.setIdProduto(idProduto);
                    novo.setReservado(0);
                    return novo;
                });
        estoque.setQuantidade(novaQuantidade);
        estoque.setAtualizadoEm(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque reservarProduto(UUID idProduto, int qtd) {
        Estoque estoque = buscarPorProduto(idProduto);
        if (estoque.getQuantidade() - estoque.getReservado() < qtd)
            throw new RuntimeException("Estoque insuficiente para reserva.");

        estoque.setReservado(estoque.getReservado() + qtd);
        estoque.setAtualizadoEm(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque liberarReserva(UUID idProduto, int qtd) {
        Estoque estoque = buscarPorProduto(idProduto);
        estoque.setReservado(Math.max(estoque.getReservado() - qtd, 0));
        estoque.setAtualizadoEm(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque movimentarSaida(UUID idProduto, int qtdVendida) {
        Estoque estoque = buscarPorProduto(idProduto);
        int disponivel = estoque.getQuantidade() - estoque.getReservado();
        if (disponivel < qtdVendida)
            throw new RuntimeException("Estoque insuficiente para saída. Disponível: " + disponivel + ", solicitado: " + qtdVendida);

        estoque.setQuantidade(estoque.getQuantidade() - qtdVendida);
        estoque.setReservado(Math.max(estoque.getReservado() - qtdVendida, 0));
        estoque.setAtualizadoEm(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }

    @Transactional
    public Estoque adicionarProduto(UUID idProduto, int qtdInicial) {
        // Upsert: cria se não existe, atualiza se existe
        Estoque estoque = estoqueRepository.findByIdProduto(idProduto)
                .orElseGet(() -> {
                    Estoque novo = new Estoque();
                    novo.setIdProduto(idProduto);
                    novo.setReservado(0);
                    return novo;
                });
        estoque.setQuantidade(qtdInicial);
        estoque.setAtualizadoEm(LocalDateTime.now());
        return estoqueRepository.save(estoque);
    }
}
