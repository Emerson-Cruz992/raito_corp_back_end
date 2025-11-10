package com.projetoIntegrador.RaitoCorp.catalogo.service;

import com.projetoIntegrador.RaitoCorp.catalogo.model.CaracteristicaIluminacao;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.CaracteristicaIluminacaoRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CaracteristicaIluminacaoService {

    private final CaracteristicaIluminacaoRepository repository;
    private final ProdutoRepository produtoRepository;

    public CaracteristicaIluminacaoService(CaracteristicaIluminacaoRepository repository,
                                           ProdutoRepository produtoRepository) {
        this.repository = repository;
        this.produtoRepository = produtoRepository;
    }

    public List<CaracteristicaIluminacao> listarPorProduto(UUID idProduto) {
        return repository.findByProdutoId(idProduto);
    }

    public CaracteristicaIluminacao adicionar(UUID idProduto,
                                              String potencia,
                                              String temperaturaCor,
                                              String fluxoLuminoso,
                                              String tensao,
                                              String eficiencia,
                                              String indiceProtecao) {
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

        return repository.save(c);
    }

    public CaracteristicaIluminacao atualizar(UUID id, CaracteristicaIluminacao dados) {
        CaracteristicaIluminacao existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Característica não encontrada"));

        existente.setPotencia(dados.getPotencia());
        existente.setTemperaturaCor(dados.getTemperaturaCor());
        existente.setFluxoLuminoso(dados.getFluxoLuminoso());
        existente.setTensao(dados.getTensao());
        existente.setEficiencia(dados.getEficiencia());
        existente.setIndiceProtecao(dados.getIndiceProtecao());

        return repository.save(existente);
    }

    public void excluir(UUID id) {
        repository.deleteById(id);
    }
}
