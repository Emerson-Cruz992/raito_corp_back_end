package com.projetoIntegrador.RaitoCorp.vendas.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "itenscarrinho")
@IdClass(ItemCarrinho.PK.class)
public class ItemCarrinho {

    @Id
    @Column(name = "id_carrinho")
    private UUID idCarrinho;

    @Id
    @Column(name = "id_produto")
    private UUID idProduto;

    private Integer quantidade;
    private BigDecimal precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrinho", insertable = false, updatable = false)
    private Carrinho carrinho;

    // ===== Classe PK =====
    public static class PK implements Serializable {
        public UUID idCarrinho;
        public UUID idProduto;

        public PK() {}
        public PK(UUID idCarrinho, UUID idProduto) {
            this.idCarrinho = idCarrinho;
            this.idProduto = idProduto;
        }

        @Override
        public int hashCode() {
            return idCarrinho.hashCode() + idProduto.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof PK)) return false;
            PK other = (PK) obj;
            return other.idCarrinho.equals(idCarrinho) && other.idProduto.equals(idProduto);
        }
    }

    // ===== Getters e Setters =====
    public UUID getIdCarrinho() { return idCarrinho; }
    public void setIdCarrinho(UUID idCarrinho) { this.idCarrinho = idCarrinho; }

    public UUID getIdProduto() { return idProduto; }
    public void setIdProduto(UUID idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
}
