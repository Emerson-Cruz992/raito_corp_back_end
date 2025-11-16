package com.projetoIntegrador.RaitoCorp.catalogo.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.catalogo.model.CaracteristicaIluminacao;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.CaracteristicaIluminacaoRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;

@Service
public class CaracteristicaIluminacaoService {

    private final CaracteristicaIluminacaoRepository repository;
    private final ProdutoRepository produtoRepository;

    public CaracteristicaIluminacaoService(CaracteristicaIluminacaoRepository repository,
                                           ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    // ========= 4.1 Criar =========
    public CaracteristicaIluminacao adicionar(
            UUID idProduto,
            String potencia,
            String temperaturaCor,
            String fluxoLuminoso,
            String tensao,
            String eficiencia,
            String indiceProtecao,
            boolean regulavel
    ) {

        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CaracteristicaIluminacao c = new CaracteristicaIluminacao();
        c.setProduto(produto);
        c.setPotencia(potencia);
        c.setTemperaturaCor(temperaturaCor);
        c.setFluxoLuminoso(fluxoLuminoso);
        c.setTensao(tensao);
        c.setEficiencia(eficiencia);
        c.setIndiceProtecao(indiceProtecao);
        c.setRegulavel(regulavel);

        return repository.save(c);
    }

    // ========= 4.2 Buscar todas por produto =========
    public List<CaracteristicaIluminacao> listarPorProduto(UUID idProduto) {
        return repository.findByProdutoId(idProduto);
    }

    // ========= 4.3 Buscar por ID =========
    public CaracteristicaIluminacao buscarPorId(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica não encontrada"));
    }

    // ========= 4.4 Atualizar =========
    public CaracteristicaIluminacao atualizar(UUID id, CaracteristicaIluminacao dados) {
        CaracteristicaIluminacao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica não encontrada"));

        // Atualiza somente se veio valor
        if (dados.getPotencia() != null) existente.setPotencia(dados.getPotencia());
        if (dados.getTemperaturaCor() != null) existente.setTemperaturaCor(dados.getTemperaturaCor());
        if (dados.getFluxoLuminoso() != null) existente.setFluxoLuminoso(dados.getFluxoLuminoso());
        if (dados.getTensao() != null) existente.setTensao(dados.getTensao());
        if (dados.getEficiencia() != null) existente.setEficiencia(dados.getEficiencia());
        if (dados.getIndiceProtecao() != null) existente.setIndiceProtecao(dados.getIndiceProtecao());

        existente.setRegulavel(dados.isRegulavel()); // boolean não aceita null

        return repository.save(existente);
    }

    // ========= 4.5 Excluir =========
    public void excluir(UUID id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Característica não encontrada");
        }
        repository.deleteById(id);
    }
}
