package com.projetoIntegrador.RaitoCorp.catalogo.dto;

import java.math.BigDecimal;

public class ProdutoAdminDTO {
    private String idProduto;
    private String nome;
    private String categoria;
    private BigDecimal preco;
    private Integer estoque;
    private Integer vendidos;
    private BigDecimal receita;
    private String urlImagem;
    private Boolean emDestaque;
    private String descricao;
    private Boolean isNovidade;
    private Boolean isPromocao;
    private BigDecimal precoOriginal;

    public ProdutoAdminDTO() {}

    public ProdutoAdminDTO(String idProduto, String nome, String categoria, BigDecimal preco,
                          Integer estoque, Integer vendidos, BigDecimal receita, String urlImagem) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
        this.vendidos = vendidos;
        this.receita = receita;
        this.urlImagem = urlImagem;
    }

    // Getters e Setters
    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Integer getVendidos() {
        return vendidos;
    }

    public void setVendidos(Integer vendidos) {
        this.vendidos = vendidos;
    }

    public BigDecimal getReceita() {
        return receita;
    }

    public void setReceita(BigDecimal receita) {
        this.receita = receita;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public Boolean getEmDestaque() {
        return emDestaque;
    }

    public void setEmDestaque(Boolean emDestaque) {
        this.emDestaque = emDestaque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getIsNovidade() {
        return isNovidade;
    }

    public void setIsNovidade(Boolean isNovidade) {
        this.isNovidade = isNovidade;
    }

    public Boolean getIsPromocao() {
        return isPromocao;
    }

    public void setIsPromocao(Boolean isPromocao) {
        this.isPromocao = isPromocao;
    }

    public BigDecimal getPrecoOriginal() {
        return precoOriginal;
    }

    public void setPrecoOriginal(BigDecimal precoOriginal) {
        this.precoOriginal = precoOriginal;
    }
}
