package com.projetoIntegrador.RaitoCorp.repository;

import com.projetoIntegrador.RaitoCorp.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, UUID> {
    List<Endereco> findByIdCliente(UUID idCliente);
    List<Endereco> findByEnderecoPrincipalTrue();
}
