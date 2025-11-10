package com.projetoIntegrador.RaitoCorp.vendas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "itenspedido")
@IdClass(ItemPedido.PK.class)
public class ItemPedido {

    @Id
    @Column(name = "id_pedido")
    private UUID idPedido;

    @Id
    @Column(name = "id_produto")
    private UUID idProduto;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido", insertable = false, updatable = false)
    private Pedido pedido;

    public static class PK implements Serializable {
        public UUID idPedido;
        public UUID idProduto;

        public PK() {}
        public PK(UUID idPedido, UUID idProduto) {
            this.idPedido = idPedido;
            this.idProduto = idProduto;
        }

        @Override
        public int hashCode() {
            return idPedido.hashCode() + idProduto.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PK)) return false;
            PK other = (PK) obj;
            return other.idPedido.equals(idPedido) && other.idProduto.equals(idProduto);
        }
    }

    // Getters e Setters
    public UUID getIdPedido() { return idPedido; }
    public void setIdPedido(UUID idPedido) { this.idPedido = idPedido; }

    public UUID getIdProduto() { return idProduto; }
    public void setIdProduto(UUID idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
}
