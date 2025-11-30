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

import com.projetoIntegrador.RaitoCorp.cadastro.dto.CriarEnderecoDTO;
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
    public ResponseEntity<?> criar(@RequestBody CriarEnderecoDTO dto) {
        try {
            // Validações básicas
            if (dto.getIdCliente() == null || dto.getIdCliente().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("ID do cliente é obrigatório");
            }
            if (dto.getCep() == null || dto.getCep().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("CEP é obrigatório");
            }
            if (dto.getRua() == null || dto.getRua().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Rua é obrigatória");
            }
            if (dto.getNumero() == null || dto.getNumero().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Número é obrigatório");
            }
            if (dto.getBairro() == null || dto.getBairro().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Bairro é obrigatório");
            }
            if (dto.getCidade() == null || dto.getCidade().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Cidade é obrigatória");
            }
            if (dto.getEstado() == null || dto.getEstado().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("Estado é obrigatório");
            }

            Endereco endereco = new Endereco();
            endereco.setIdCliente(UUID.fromString(dto.getIdCliente()));
            endereco.setCep(dto.getCep().replaceAll("[^0-9]", "")); // Remove formatação
            endereco.setRua(dto.getRua());
            endereco.setNumero(dto.getNumero());
            endereco.setComplemento(dto.getComplemento());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setEstado(dto.getEstado().toUpperCase());
            endereco.setEnderecoPrincipal(dto.getEnderecoPrincipal() != null && dto.getEnderecoPrincipal());

            Endereco novo = enderecoService.criarEndereco(endereco);
            return ResponseEntity.ok(novo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID do cliente inválido: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar endereço: " + e.getMessage());
        }
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
