package com.projetoIntegrador.RaitoCorp.catalogo.service;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Modelo3D;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.Modelo3DRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class Modelo3DService {

    private final Modelo3DRepository modelo3DRepository;
    private final ProdutoRepository produtoRepository;

    public Modelo3DService(Modelo3DRepository modelo3DRepository, ProdutoRepository produtoRepository) {
        this.modelo3DRepository = modelo3DRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<Modelo3D> listarPorProduto(UUID idProduto) {
        return modelo3DRepository.findByProdutoId(idProduto);
    }

    public Modelo3D adicionarModelo(UUID idProduto, String nomeArquivo, String url, String formato) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Modelo3D modelo = new Modelo3D();
        modelo.setProduto(produto);
        modelo.setNomeArquivo(nomeArquivo);
        modelo.setUrl(url);
        modelo.setFormato(formato);

        return modelo3DRepository.save(modelo);
    }

    public void excluir(UUID id) {
        modelo3DRepository.deleteById(id);
    }
}
