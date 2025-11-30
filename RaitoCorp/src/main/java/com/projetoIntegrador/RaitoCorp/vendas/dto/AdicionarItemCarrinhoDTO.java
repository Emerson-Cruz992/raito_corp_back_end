package com.projetoIntegrador.RaitoCorp.vendas.dto;

import java.math.BigDecimal;

public class AdicionarItemCarrinhoDTO {
    private String idProduto;
    private Integer quantidade;
    private BigDecimal preco;

    public AdicionarItemCarrinhoDTO() {}

    public AdicionarItemCarrinhoDTO(String idProduto, Integer quantidade, BigDecimal preco) {
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(String idProduto) {
        this.idProduto = idProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
