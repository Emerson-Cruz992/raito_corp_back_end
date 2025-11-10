package com.projetoIntegrador.RaitoCorp.catalogo.controller;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Modelo3D;
import com.projetoIntegrador.RaitoCorp.catalogo.service.Modelo3DService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modelos3d")
@CrossOrigin(origins = "*")
public class Modelo3DController {

    private final Modelo3DService modelo3DService;

    public Modelo3DController(Modelo3DService modelo3DService) {
        this.modelo3DService = modelo3DService;
    }

    @GetMapping("/produto/{idProduto}")
    public ResponseEntity<List<Modelo3D>> listarPorProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(modelo3DService.listarPorProduto(idProduto));
    }

    @PostMapping("/produto/{idProduto}")
    public ResponseEntity<Modelo3D> adicionarModelo(
            @PathVariable UUID idProduto,
            @RequestParam String nomeArquivo,
            @RequestParam String url,
            @RequestParam String formato
    ) {
        return ResponseEntity.ok(modelo3DService.adicionarModelo(idProduto, nomeArquivo, url, formato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        modelo3DService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
