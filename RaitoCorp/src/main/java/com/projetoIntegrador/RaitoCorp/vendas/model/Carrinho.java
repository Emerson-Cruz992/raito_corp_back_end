package com.projetoIntegrador.RaitoCorp.vendas.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "carrinhos")
public class Carrinho {

    @Id
    @GeneratedValue
    @Column(name = "id_carrinho")
    private UUID idCarrinho;

    @Column(name = "id_cliente", unique = true, nullable = false)
    private UUID idCliente;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm = LocalDateTime.now();

    @OneToMany(mappedBy = "carrinho", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ItemCarrinho> itens = new ArrayList<>();

    // ===== Getters e Setters =====
    public UUID getIdCarrinho() { return idCarrinho; }
    public void setIdCarrinho(UUID idCarrinho) { this.idCarrinho = idCarrinho; }

    public UUID getIdCliente() { return idCliente; }
    public void setIdCliente(UUID idCliente) { this.idCliente = idCliente; }

    public LocalDateTime getCriadoEm() { return criadoEm; }
    public void setCriadoEm(LocalDateTime criadoEm) { this.criadoEm = criadoEm; }

    public List<ItemCarrinho> getItens() { return itens; }
    public void setItens(List<ItemCarrinho> itens) { this.itens = itens; }
}
