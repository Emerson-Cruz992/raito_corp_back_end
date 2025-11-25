package com.projetoIntegrador.RaitoCorp.cadastro.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoIntegrador.RaitoCorp.cadastro.dto.LoginResponse;
import com.projetoIntegrador.RaitoCorp.cadastro.model.Credencial;
import com.projetoIntegrador.RaitoCorp.cadastro.model.Usuario;
import com.projetoIntegrador.RaitoCorp.cadastro.service.CredencialService;
import com.projetoIntegrador.RaitoCorp.cadastro.service.UsuarioService;

@RestController
@RequestMapping("/api/credenciais")
public class CredencialController {

    private final CredencialService credencialService;
    private final UsuarioService usuarioService;

    public CredencialController(CredencialService credencialService, UsuarioService usuarioService) {
        this.credencialService = credencialService;
        this.usuarioService = usuarioService;
    }

    // Criar credencial
    @PostMapping("/criar")
    public ResponseEntity<Credencial> criar(@RequestBody Credencial credencial) {
        Credencial nova = credencialService.criarCredencial(credencial);
        return ResponseEntity.ok(nova);
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestParam String email, @RequestParam String senha) {
        Optional<Credencial> credencialOpt = credencialService.autenticarERetornar(email, senha);

        if (credencialOpt.isEmpty()) {
            return ResponseEntity.status(401).build();
        }

        Credencial credencial = credencialOpt.get();
        Optional<Usuario> usuarioOpt = usuarioService.buscarPorId(credencial.getIdUsuario());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }

        Usuario usuario = usuarioOpt.get();
        LoginResponse response = new LoginResponse(
            credencial.getIdUsuario().toString(),
            credencial.getEmail(),
            usuario.getNome()
        );

        return ResponseEntity.ok(response);
    }

    // Buscar credencial por email
    @GetMapping("/email/{email}")
    public ResponseEntity<Credencial> buscarPorEmail(@PathVariable String email) {
        Optional<Credencial> credencial = credencialService.buscarPorEmail(email);
        return credencial.map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

}
