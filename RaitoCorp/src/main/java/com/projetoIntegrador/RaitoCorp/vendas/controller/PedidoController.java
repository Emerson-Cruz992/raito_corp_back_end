package com.projetoIntegrador.RaitoCorp.vendas.controller;

import com.projetoIntegrador.RaitoCorp.vendas.model.Pedido;
import com.projetoIntegrador.RaitoCorp.vendas.service.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "*")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> listarPorCliente(@PathVariable UUID idCliente) {
        return ResponseEntity.ok(pedidoService.listarPorCliente(idCliente));
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Pedido> finalizarPedido(
            @RequestParam UUID idCliente,
            @RequestParam UUID idCarrinho,
            @RequestParam(required = false) UUID idEnderecoEntrega
    ) {
        return ResponseEntity.ok(pedidoService.finalizarPedido(idCliente, idCarrinho, idEnderecoEntrega));
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable UUID idPedido) {
        return pedidoService.buscarPorId(idPedido)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{idPedido}/status")
    public ResponseEntity<Pedido> atualizarStatus(
            @PathVariable UUID idPedido,
            @RequestParam String status
    ) {
        return ResponseEntity.ok(pedidoService.atualizarStatus(idPedido, status));
    }
}
