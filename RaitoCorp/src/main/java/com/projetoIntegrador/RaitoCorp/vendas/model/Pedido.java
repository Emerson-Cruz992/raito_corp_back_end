package com.projetoIntegrador.RaitoCorp.vendas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue
    @Column(name = "id_pedido")
    private UUID idPedido;

    @Column(name = "id_cliente", nullable = false)
    private UUID idCliente;

    @Column(name = "id_endereco_entrega")
    private UUID idEnderecoEntrega;

    @Column(name = "valor_total", nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private String status = "PENDENTE";

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    // ===== Getters e Setters =====
    public UUID getIdPedido() { return idPedido; }
    public void setIdPedido(UUID idPedido) { this.idPedido = idPedido; }

    public UUID getIdCliente() { return idCliente; }
    public void setIdCliente(UUID idCliente) { this.idCliente = idCliente; }

    public UUID getIdEnderecoEntrega() { return idEnderecoEntrega; }
    public void setIdEnderecoEntrega(UUID idEnderecoEntrega) { this.idEnderecoEntrega = idEnderecoEntrega; }

    public BigDecimal getValorTotal() { return valorTotal; }
    public void setValorTotal(BigDecimal valorTotal) { this.valorTotal = valorTotal; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public List<ItemPedido> getItens() { return itens; }
    public void setItens(List<ItemPedido> itens) { this.itens = itens; }
}
