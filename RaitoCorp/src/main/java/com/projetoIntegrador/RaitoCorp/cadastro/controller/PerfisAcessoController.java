package com.projetoIntegrador.RaitoCorp.cadastro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.cadastro.model.PerfilAcesso;
import com.projetoIntegrador.RaitoCorp.cadastro.service.PerfisAcessoService;

@RestController
@RequestMapping("/api/perfis")
@CrossOrigin(origins = "*")
public class PerfisAcessoController {

    private final PerfisAcessoService service;

    public PerfisAcessoController(PerfisAcessoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PerfilAcesso> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public ResponseEntity<PerfilAcesso> criar(@RequestBody PerfilAcesso perfil) {
        return ResponseEntity.ok(service.criar(perfil));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilAcesso> atualizar(@PathVariable UUID id, @RequestBody PerfilAcesso perfil) {
        return ResponseEntity.ok(service.atualizar(id, perfil));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
