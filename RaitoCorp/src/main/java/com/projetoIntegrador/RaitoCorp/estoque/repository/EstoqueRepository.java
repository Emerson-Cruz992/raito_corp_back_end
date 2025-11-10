package com.projetoIntegrador.RaitoCorp.estoque.repository;

import com.projetoIntegrador.RaitoCorp.estoque.model.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface EstoqueRepository extends JpaRepository<Estoque, UUID> {
    Optional<Estoque> findByIdProduto(UUID idProduto);
}
