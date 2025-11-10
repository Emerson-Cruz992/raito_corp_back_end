package com.projetoIntegrador.RaitoCorp.catalogo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Categoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoriaId;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.CategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoCategoriaRepository;
import com.projetoIntegrador.RaitoCorp.catalogo.repository.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoCategoriaRepository produtoCategoriaRepository;

    public ProdutoService(ProdutoRepository produtoRepository,
                          CategoriaRepository categoriaRepository,
                          ProdutoCategoriaRepository produtoCategoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoCategoriaRepository = produtoCategoriaRepository;
    }

    // =========================================================
    // CRUD BÁSICO DE PRODUTOS
    // =========================================================

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(UUID id) {
        return produtoRepository.findById(id);
    }

    public Produto criarProduto(Produto produto) {
        produto.setId(null);
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    public Produto atualizarProduto(UUID id, Produto atualizado) {
        Produto existente = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        existente.setNome(atualizado.getNome());
        existente.setDescricao(atualizado.getDescricao());
        existente.setPreco(atualizado.getPreco());
        existente.setAtivo(atualizado.isAtivo());
        return produtoRepository.save(existente);
    }

    public void excluirProduto(UUID id) {
        produtoRepository.deleteById(id);
    }

    // =========================================================
    // RELACIONAMENTO COM CATEGORIAS
    // =========================================================

    @Transactional
    public void associarCategoria(UUID idProduto, UUID idCategoria) {
        Produto produto = produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(idCategoria)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        ProdutoCategoria produtoCategoria = new ProdutoCategoria(produto, categoria);
        produtoCategoriaRepository.save(produtoCategoria);
    }

    @Transactional
    public List<String> listarCategoriasDoProduto(UUID idProduto) {
        return produtoCategoriaRepository.findByProdutoId(idProduto)
                .stream()
                .map(pc -> {
                    Categoria categoria = pc.getCategoria();
                    // força inicialização antes do fechamento da sessão
                    return categoria != null ? categoria.getNome() : "Categoria desconhecida";
                })
                .collect(Collectors.toList());
    }


    @Transactional
    public void removerCategoria(UUID idProduto, UUID idCategoria) {
        ProdutoCategoriaId id = new ProdutoCategoriaId(idProduto, idCategoria);
        produtoCategoriaRepository.deleteById(id);
    }

    
}
