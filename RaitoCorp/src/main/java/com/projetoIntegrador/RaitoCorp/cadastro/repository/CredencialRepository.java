package com.projetoIntegrador.RaitoCorp.cadastro.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Credencial;

@Repository
public interface CredencialRepository extends JpaRepository<Credencial, UUID> {
    Optional<Credencial> findByEmail(String email);
    Optional<Credencial> findByIdUsuario(UUID idUsuario);
}
