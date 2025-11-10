package com.projetoIntegrador.RaitoCorp.cadastro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Credencial;
import com.projetoIntegrador.RaitoCorp.cadastro.service.CredencialService;

@RestController
@RequestMapping("/api/credenciais")
public class CredencialController {

    private final CredencialService credencialService;

    public CredencialController(CredencialService credencialService) {
        this.credencialService = credencialService;
    }

    // Criar credencial
    @PostMapping("/criar")
    public ResponseEntity<Credencial> criar(@RequestBody Credencial credencial) {
        Credencial nova = credencialService.criarCredencial(credencial);
        return ResponseEntity.ok(nova);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String senha) {
        boolean sucesso = credencialService.autenticar(email, senha);
        if (sucesso) {
            return ResponseEntity.ok("✅ Login realizado com sucesso!");
        } else {
            return ResponseEntity.status(401).body("❌ Credenciais inválidas!");
        }
    }

}
