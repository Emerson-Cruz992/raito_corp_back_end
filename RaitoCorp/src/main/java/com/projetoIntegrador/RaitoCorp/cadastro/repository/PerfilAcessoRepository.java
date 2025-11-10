package com.projetoIntegrador.RaitoCorp.cadastro.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador.RaitoCorp.cadastro.model.PerfilAcesso;

public interface PerfilAcessoRepository extends JpaRepository<PerfilAcesso, UUID> {
    Optional<PerfilAcesso> findByNome(String nome);
}
