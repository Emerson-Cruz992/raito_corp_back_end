package com.projetoIntegrador.RaitoCorp.cadastro.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    List<Endereco> findByIdCliente(UUID idCliente);
}
