package com.projetoIntegrador.RaitoCorp.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.model.Usuario;
import com.projetoIntegrador.RaitoCorp.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepo;

    public UsuarioService(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepo.findAll();
    }

    public Optional<Usuario> buscarPorId(UUID id) {
        return usuarioRepo.findById(id);
    }

    public void deletarUsuario(UUID id) {
        usuarioRepo.deleteById(id);
    }
}
