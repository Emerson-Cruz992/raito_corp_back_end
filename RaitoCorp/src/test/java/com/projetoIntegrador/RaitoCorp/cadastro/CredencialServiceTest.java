package com.projetoIntegrador.RaitoCorp.cadastro;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Credencial;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.CredencialRepository;
import com.projetoIntegrador.RaitoCorp.cadastro.service.CredencialService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CredencialServiceTest {

    private CredencialRepository credencialRepository;
    private CredencialService credencialService;

    @BeforeEach
    void setUp() {
        credencialRepository = Mockito.mock(CredencialRepository.class);
        credencialService = new CredencialService(credencialRepository);
    }

    @Test
    void deveCriarCredencialComSenhaCriptografada() {
        Credencial credencial = new Credencial();
        credencial.setEmail("teste@exemplo.com");
        credencial.setSenhaHash("123456");

        when(credencialRepository.save(any(Credencial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Credencial resultado = credencialService.criarCredencial(credencial);

        assertNotNull(resultado.getSenhaHash());
        assertTrue(BCrypt.checkpw("123456", resultado.getSenhaHash()));
        verify(credencialRepository, times(1)).save(any(Credencial.class));
    }

    @Test
    void deveAutenticarCredencialValida() {
        String senhaHash = BCrypt.hashpw("senha123", BCrypt.gensalt());
        Credencial credencial = new Credencial(UUID.randomUUID(), "user@email.com", senhaHash);

        when(credencialRepository.findByEmail("user@email.com")).thenReturn(Optional.of(credencial));
        when(credencialRepository.save(any(Credencial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Credencial> resultado = credencialService.autenticarERetornar("user@email.com", "senha123");

        assertTrue(resultado.isPresent());
        assertEquals("user@email.com", resultado.get().getEmail());
    }

    @Test
    void deveRecusarSenhaInvalida() {
        String senhaHash = BCrypt.hashpw("correta", BCrypt.gensalt());
        Credencial credencial = new Credencial(UUID.randomUUID(), "user@email.com", senhaHash);

        when(credencialRepository.findByEmail("user@email.com")).thenReturn(Optional.of(credencial));
        when(credencialRepository.save(any(Credencial.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Optional<Credencial> resultado = credencialService.autenticarERetornar("user@email.com", "errada");

        assertTrue(resultado.isEmpty());
    }

    @Test
    void deveRecusarEmailInexistente() {
        when(credencialRepository.findByEmail("naoexiste@email.com")).thenReturn(Optional.empty());

        Optional<Credencial> resultado = credencialService.autenticarERetornar("naoexiste@email.com", "qualquer");

        assertTrue(resultado.isEmpty());
    }
}
