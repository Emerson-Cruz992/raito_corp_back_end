package com.projetoIntegrador.RaitoCorp.cadastro;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Usuario;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.UsuarioRepository;
import com.projetoIntegrador.RaitoCorp.cadastro.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    private UsuarioRepository usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioRepository = Mockito.mock(UsuarioRepository.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    void deveCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Lucas");
        usuario.setSobrenome("Pereira");
        usuario.setTipoUsuario("administrador");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario salvo = usuarioService.criarUsuario(usuario);

        assertNotNull(salvo);
        assertEquals("Lucas", salvo.getNome());
    }

    @Test
    void deveBuscarUsuarioPorId() {
        UUID id = UUID.randomUUID();
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setNome("Marina");

        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));

        Optional<Usuario> resultado = usuarioService.buscarPorId(id);

        assertTrue(resultado.isPresent());
        assertEquals("Marina", resultado.get().getNome());
    }
}
