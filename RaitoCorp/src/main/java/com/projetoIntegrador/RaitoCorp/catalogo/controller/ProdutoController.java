package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    // =========================================================
    // CRUD BÁSICO DE PRODUTOS
    // =========================================================

    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable UUID id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novo = produtoService.criarProduto(produto);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable UUID id,
            @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable UUID id) {
        produtoService.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }

    // =========================================================
    // RELACIONAMENTO COM CATEGORIA
    // =========================================================

    @PostMapping("/{idProduto}/categorias/{idCategoria}")
    public ResponseEntity<String> associarCategoria(
            @PathVariable UUID idProduto,
            @PathVariable UUID idCategoria) {
        produtoService.associarCategoria(idProduto, idCategoria);
        return ResponseEntity.ok("Categoria associada ao produto com sucesso!");
    }

    @GetMapping("/{idProduto}/categorias")
    public ResponseEntity<List<String>> listarCategoriasDoProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(produtoService.listarCategoriasDoProduto(idProduto));
    }

    @DeleteMapping("/{idProduto}/categorias/{idCategoria}")
    public ResponseEntity<String> removerCategoria(
            @PathVariable UUID idProduto,
            @PathVariable UUID idCategoria) {
        produtoService.removerCategoria(idProduto, idCategoria);
        return ResponseEntity.ok("Categoria removida do produto com sucesso!");
    }
}
