package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import com.projetoIntegrador.RaitoCorp.catalogo.service.ProdutoService;

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
    public ResponseEntity<List<Produto>> listarTodos(@RequestParam(required = false) Boolean emDestaque) {
        if (emDestaque != null && emDestaque) {
            return ResponseEntity.ok(produtoService.listarProdutosEmDestaque());
        }
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
// Funcioana mas tem que definir novamente todos os itens

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable UUID id,
            @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(atualizado);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Produto> atualizarParcial(
            @PathVariable UUID id,
            @RequestBody Produto produto) {
        Produto atualizado = produtoService.atualizarParcial(id, produto);
        return ResponseEntity.ok(atualizado);
    }
//Não funciona 
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

    @PostMapping("/{idProduto}/categoria-nome/{nomeCategoria}")
    public ResponseEntity<Produto> associarCategoriaPorNome(
            @PathVariable UUID idProduto,
            @PathVariable String nomeCategoria) {
        produtoService.associarCategoriaPorNome(idProduto, nomeCategoria);
        Produto produto = produtoService.buscarPorId(idProduto)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ResponseEntity.ok(produto);
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

    // =========================================================
    // UPLOAD DE IMAGEM
    // =========================================================

    @PostMapping("/{idProduto}/imagem")
    public ResponseEntity<Produto> uploadImagem(
            @PathVariable UUID idProduto,
            @RequestParam("imagem") MultipartFile imagem) {
        try {
            System.out.println("Recebendo upload de imagem para produto: " + idProduto);
            System.out.println("Nome do arquivo: " + imagem.getOriginalFilename());
            System.out.println("Tamanho do arquivo: " + imagem.getSize());
            Produto produtoAtualizado = produtoService.uploadImagem(idProduto, imagem);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (Exception e) {
            System.err.println("Erro ao fazer upload de imagem: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
