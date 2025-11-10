package com.projetoIntegrador.RaitoCorp.vendas.repository;

import com.projetoIntegrador.RaitoCorp.vendas.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface CarrinhoRepository extends JpaRepository<Carrinho, UUID> {
    Optional<Carrinho> findByIdCliente(UUID idCliente);
}
