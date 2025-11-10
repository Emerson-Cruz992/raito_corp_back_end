package com.projetoIntegrador.RaitoCorp.cadastro.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
    Optional<Cliente> findByCpf(String cpf);
    List<Cliente> findByIdUsuario(UUID idUsuario);
}
