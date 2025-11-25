package com.projetoIntegrador.RaitoCorp.vendas.repository;

import com.projetoIntegrador.RaitoCorp.vendas.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedido.PK> {
    List<ItemPedido> findByIdPedido(UUID idPedido);
    List<ItemPedido> findByIdProduto(UUID idProduto);
}
