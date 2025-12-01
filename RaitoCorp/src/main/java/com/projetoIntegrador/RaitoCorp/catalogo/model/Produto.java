package com.projetoIntegrador.RaitoCorp.catalogo.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

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

    @Column(name = "is_novidade")
    private Boolean isNovidade = false;

    @Column(name = "is_promocao")
    private Boolean isPromocao = false;

    @Column(name = "preco_original")
    private BigDecimal precoOriginal;

    @Lob
    @Column(name = "imagem_url", columnDefinition = "TEXT")
    private String imagemUrl;

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
        return isNovidade;
    }

    public void setNovidade(Boolean novidade) {
        isNovidade = novidade;
    }

    public Boolean isPromocao() {
        return isPromocao;
    }

    public void setPromocao(Boolean promocao) {
        isPromocao = promocao;
    }

    public BigDecimal getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(BigDecimal precoOriginal) {
        this.precoOriginal = precoOriginal;
    }
}
