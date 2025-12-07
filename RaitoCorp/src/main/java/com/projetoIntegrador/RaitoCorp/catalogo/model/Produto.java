package com.projetoIntegrador.RaitoCorp.catalogo.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private Boolean ativo = true;

    @Column(name = "em_destaque")
    private Boolean emDestaque = false;

    @JsonProperty("isNovidade")
    @Column(name = "is_novidade")
    private Boolean novidade = false;

    @JsonProperty("isPromocao")
    @Column(name = "is_promocao")
    private Boolean promocao = false;

    @Column(name = "preco_original")
    private BigDecimal precoOriginal;

    @Lob
    @Column(name = "imagem_url", columnDefinition = "TEXT")
    private String imagemUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "produtos_categorias",
        joinColumns = @JoinColumn(name = "produto_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private Set<Categoria> categorias = new HashSet<>();

    public Produto() {}

    public Produto(String nome, String descricao, BigDecimal preco, Boolean ativo) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean isEmDestaque() {
        return emDestaque;
    }

    public void setEmDestaque(Boolean emDestaque) {
        this.emDestaque = emDestaque;
    }

    public String getImagemUrl() {
        return imagemUrl;
    }

    public void setImagemUrl(String imagemUrl) {
        this.imagemUrl = imagemUrl;
    }

    public Boolean isNovidade() {
        return novidade;
    }

    public void setNovidade(Boolean novidade) {
        this.novidade = novidade;
    }

    public Boolean isPromocao() {
        return promocao;
    }

    public void setPromocao(Boolean promocao) {
        this.promocao = promocao;
    }

    public BigDecimal getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(BigDecimal precoOriginal) {
        this.precoOriginal = precoOriginal;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }
}
