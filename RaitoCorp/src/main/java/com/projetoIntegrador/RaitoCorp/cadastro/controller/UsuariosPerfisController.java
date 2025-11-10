package com.projetoIntegrador.RaitoCorp.cadastro.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.projetoIntegrador.RaitoCorp.cadastro.model.PerfilAcesso;
import com.projetoIntegrador.RaitoCorp.cadastro.model.Usuario;
import com.projetoIntegrador.RaitoCorp.cadastro.model.UsuariosPerfis;
import com.projetoIntegrador.RaitoCorp.cadastro.service.UsuarioPerfilService;

@RestController
@RequestMapping("/api/usuarios-perfis")
public class UsuariosPerfisController {

    private final UsuarioPerfilService service;

    public UsuariosPerfisController(UsuarioPerfilService service) {
        this.service = service;
    }

    /** Atribui perfil a um usuário **/
    @PostMapping("/atribuir")
    public ResponseEntity<UsuariosPerfis> atribuirPerfil(
            @RequestParam UUID idUsuario,
            @RequestParam UUID idPerfil) {
        return ResponseEntity.ok(service.atribuirPerfil(idUsuario, idPerfil));
    }

    /** Remove vínculo entre usuário e perfil **/
    @DeleteMapping("/remover")
    public ResponseEntity<Void> removerPerfil(
            @RequestParam UUID idUsuario,
            @RequestParam UUID idPerfil) {
        service.removerPerfil(idUsuario, idPerfil);
        return ResponseEntity.noContent().build();
    }

    /** Lista todos os perfis (com nome e descrição) de um usuário **/
    @GetMapping("/listar-perfis-usuario")
    public ResponseEntity<List<PerfilAcesso>> listarPerfisUsuario(@RequestParam UUID idUsuario) {
        return ResponseEntity.ok(service.listarPerfisDetalhadosPorUsuario(idUsuario));
    }

    /** Lista todos os usuários (com nome e e-mail) de um perfil **/
    @GetMapping("/listar-usuarios-perfil")
    public ResponseEntity<List<Usuario>> listarUsuariosPorPerfil(@RequestParam UUID idPerfil) {
        return ResponseEntity.ok(service.listarUsuariosPorPerfil(idPerfil));
    }

    /** Lista todos os vínculos usuário <-> perfil **/
    @GetMapping("/listar-todos")
    public ResponseEntity<List<UsuariosPerfis>> listarTodos() {
        return ResponseEntity.ok(service.listarTodosVinculos());
    }
}
