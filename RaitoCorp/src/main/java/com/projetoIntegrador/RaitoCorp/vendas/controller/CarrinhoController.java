package com.projetoIntegrador.RaitoCorp.vendas.controller;

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
    public ResponseEntity<Carrinho> criarCarrinho(@RequestParam UUID idCliente) {
        return ResponseEntity.ok(carrinhoService.obterOuCriarCarrinho(idCliente));
    }

    @GetMapping("/{idCarrinho}/itens")
    public ResponseEntity<List<ItemCarrinho>> listarItens(@PathVariable UUID idCarrinho) {
        return ResponseEntity.ok(carrinhoService.listarItens(idCarrinho));
    }

    @PostMapping("/{idCarrinho}/adicionar")
    public ResponseEntity<ItemCarrinho> adicionarItem(
            @PathVariable UUID idCarrinho,
            @RequestParam UUID idProduto,
            @RequestParam Integer quantidade,
            @RequestParam BigDecimal preco
    ) {
        return ResponseEntity.ok(carrinhoService.adicionarItem(idCarrinho, idProduto, quantidade, preco));
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
