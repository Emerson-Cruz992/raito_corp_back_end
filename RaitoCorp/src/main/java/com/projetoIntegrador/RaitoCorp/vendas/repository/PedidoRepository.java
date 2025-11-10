package com.projetoIntegrador.RaitoCorp.vendas.repository;

import com.projetoIntegrador.RaitoCorp.vendas.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    List<Pedido> findByIdCliente(UUID idCliente);
}
