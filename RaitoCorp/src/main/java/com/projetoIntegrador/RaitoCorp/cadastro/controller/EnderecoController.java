package com.projetoIntegrador.RaitoCorp.cadastro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Endereco;
import com.projetoIntegrador.RaitoCorp.cadastro.service.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Endereco> criar(@RequestBody Endereco endereco) {
        Endereco novo = enderecoService.criarEndereco(endereco);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/listar")
    public List<Endereco> listar() {
        return enderecoService.listarEnderecos();
    }

    @GetMapping("/cliente/{idCliente}")
    public List<Endereco> listarPorCliente(@PathVariable UUID idCliente) {
        return enderecoService.listarPorCliente(idCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> buscarPorId(@PathVariable UUID id) {
        return enderecoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        enderecoService.deletarEndereco(id);
        return ResponseEntity.ok("🗑️ Endereço removido com sucesso!");
    }
}
