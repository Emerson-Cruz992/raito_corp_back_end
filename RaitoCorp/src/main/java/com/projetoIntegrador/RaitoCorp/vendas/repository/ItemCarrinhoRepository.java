package com.projetoIntegrador.RaitoCorp.vendas.repository;

import com.projetoIntegrador.RaitoCorp.vendas.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinho, ItemCarrinho.PK> {
    List<ItemCarrinho> findByIdCarrinho(UUID idCarrinho);
}
