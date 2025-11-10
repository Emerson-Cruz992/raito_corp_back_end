package com.projetoIntegrador.RaitoCorp.estoque.controller;

import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import com.projetoIntegrador.RaitoCorp.estoque.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/estoque")
@CrossOrigin(origins = "*")
public class EstoqueController {

    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    public ResponseEntity<List<Estoque>> listarTodos() {
        return ResponseEntity.ok(estoqueService.listarTodos());
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Estoque> buscarPorProduto(@PathVariable UUID idProduto) {
        return ResponseEntity.ok(estoqueService.buscarPorProduto(idProduto));
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Estoque> adicionarProduto(
            @RequestParam UUID idProduto,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.adicionarProduto(idProduto, quantidade));
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Estoque> atualizarQuantidade(
            @RequestParam UUID idProduto,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.atualizarQuantidade(idProduto, quantidade));
    }

    @PutMapping("/reservar")
    public ResponseEntity<Estoque> reservar(
            @RequestParam UUID idProduto,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.reservarProduto(idProduto, quantidade));
    }

    @PutMapping("/liberar")
    public ResponseEntity<Estoque> liberarReserva(
            @RequestParam UUID idProduto,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.liberarReserva(idProduto, quantidade));
    }

    @PutMapping("/saida")
    public ResponseEntity<Estoque> movimentarSaida(
            @RequestParam UUID idProduto,
            @RequestParam int quantidade
    ) {
        return ResponseEntity.ok(estoqueService.movimentarSaida(idProduto, quantidade));
    }
}
