package com.projetoIntegrador.RaitoCorp.controller;

import com.projetoIntegrador.RaitoCorp.model.Cliente;
import com.projetoIntegrador.RaitoCorp.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping("/criar")
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novo = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novo);
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {
        return clienteService.listarClientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable UUID id) {
        return clienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        return clienteService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{idUsuario}")
    public List<Cliente> listarPorUsuario(@PathVariable UUID idUsuario) {
        return clienteService.buscarPorUsuario(idUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable UUID id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.ok("🗑️ Cliente removido com sucesso!");
    }
}
