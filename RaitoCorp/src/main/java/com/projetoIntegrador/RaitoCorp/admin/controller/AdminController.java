package com.projetoIntegrador.RaitoCorp.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.admin.service.AdminService;
import com.projetoIntegrador.RaitoCorp.catalogo.dto.ProdutoAdminDTO;
import com.projetoIntegrador.RaitoCorp.vendas.dto.PedidoAdminDTO;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoAdminDTO>> listarProdutosAdmin() {
        List<ProdutoAdminDTO> produtos = adminService.listarProdutosComMetricas();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoAdminDTO>> listarPedidosAdmin() {
        List<PedidoAdminDTO> pedidos = adminService.listarPedidosComClientes();
        return ResponseEntity.ok(pedidos);
    }
}
