package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import com.projetoIntegrador.RaitoCorp.catalogo.model.ImagemProduto;
import com.projetoIntegrador.RaitoCorp.catalogo.service.ImagemProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/imagens")
@CrossOrigin(origins = "*")
public class ImagemProdutoController {

    private final ImagemProdutoService imagemProdutoService;

    public ImagemProdutoController(ImagemProdutoService imagemProdutoService) {
        this.imagemProdutoService = imagemProdutoService;
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<ImagemProduto>> listarPorProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(imagemProdutoService.listarPorProduto(idProduto));
    }

    @PostMapping("/produto/{idProduto}")
    public ResponseEntity<ImagemProduto> adicionarImagem(
            @PathVariable UUID idProduto,
            @RequestParam String url,
            @RequestParam(defaultValue = "false") boolean principal
    ) {
        return ResponseEntity.ok(imagemProdutoService.adicionarImagem(idProduto, url, principal));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImagem(@PathVariable UUID id) {
        imagemProdutoService.excluirImagem(id);
        return ResponseEntity.noContent().build();
    }
}
