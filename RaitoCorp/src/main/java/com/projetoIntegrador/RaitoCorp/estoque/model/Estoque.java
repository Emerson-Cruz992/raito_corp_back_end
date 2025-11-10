package com.projetoIntegrador.RaitoCorp.estoque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue
    @Column(name = "id_estoque")
    private UUID idEstoque;

    @Column(name = "id_produto", unique = true, nullable = false)
    private UUID idProduto;

    @Column(nullable = false)
    private Integer quantidade = 0;

    private Integer reservado = 0;

    @Column(name = "atualizado_em")
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    // ===== Getters e Setters =====
    public UUID getIdEstoque() { return idEstoque; }
    public void setIdEstoque(UUID idEstoque) { this.idEstoque = idEstoque; }

    public UUID getIdProduto() { return idProduto; }
    public void setIdProduto(UUID idProduto) { this.idProduto = idProduto; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Integer getReservado() { return reservado; }
    public void setReservado(Integer reservado) { this.reservado = reservado; }

    public LocalDateTime getAtualizadoEm() { return atualizadoEm; }
    public void setAtualizadoEm(LocalDateTime atualizadoEm) { this.atualizadoEm = atualizadoEm; }
}
