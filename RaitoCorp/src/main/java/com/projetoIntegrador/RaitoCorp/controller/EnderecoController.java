package com.projetoIntegrador.RaitoCorp.controller;

import com.projetoIntegrador.RaitoCorp.model.Endereco;
import com.projetoIntegrador.RaitoCorp.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
