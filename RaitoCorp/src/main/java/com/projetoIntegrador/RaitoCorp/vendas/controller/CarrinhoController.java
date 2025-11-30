package com.projetoIntegrador.RaitoCorp.vendas.controller;

import com.projetoIntegrador.RaitoCorp.vendas.dto.AdicionarItemCarrinhoDTO;
import com.projetoIntegrador.RaitoCorp.vendas.dto.CriarCarrinhoDTO;
import com.projetoIntegrador.RaitoCorp.vendas.model.*;
import com.projetoIntegrador.RaitoCorp.vendas.service.CarrinhoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/carrinho")
@CrossOrigin(origins = "*")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarCarrinho(@RequestBody CriarCarrinhoDTO dto) {
        try {
            UUID clienteUUID = UUID.fromString(dto.getIdCliente());
            return ResponseEntity.ok(carrinhoService.obterOuCriarCarrinho(clienteUUID));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID do cliente inválido: " + dto.getIdCliente());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar carrinho: " + e.getMessage());
        }
    }

    @GetMapping("/{idCarrinho}/itens")
    public ResponseEntity<List<ItemCarrinho>> listarItens(@PathVariable UUID idCarrinho) {
        return ResponseEntity.ok(carrinhoService.listarItens(idCarrinho));
    }

    @PostMapping("/{idCarrinho}/adicionar")
    public ResponseEntity<?> adicionarItem(
            @PathVariable UUID idCarrinho,
            @RequestBody AdicionarItemCarrinhoDTO dto
    ) {
        try {
            UUID produtoUUID = UUID.fromString(dto.getIdProduto());
            return ResponseEntity.ok(carrinhoService.adicionarItem(idCarrinho, produtoUUID, dto.getQuantidade(), dto.getPreco()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ID do produto inválido: " + dto.getIdProduto());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao adicionar item: " + e.getMessage());
        }
    }

    @DeleteMapping("/{idCarrinho}/remover/{idProduto}")
    public ResponseEntity<Void> removerItem(
            @PathVariable UUID idCarrinho,
            @PathVariable UUID idProduto
    ) {
        carrinhoService.removerItem(idCarrinho, idProduto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idCarrinho}/limpar")
    public ResponseEntity<Void> limparCarrinho(@PathVariable UUID idCarrinho) {
        carrinhoService.limparCarrinho(idCarrinho);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idCarrinho}/total")
    public ResponseEntity<BigDecimal> calcularTotal(@PathVariable UUID idCarrinho) {
        return ResponseEntity.ok(carrinhoService.calcularTotal(idCarrinho));
    }
}
