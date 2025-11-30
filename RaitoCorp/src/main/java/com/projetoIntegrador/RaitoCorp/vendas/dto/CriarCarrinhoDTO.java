package com.projetoIntegrador.RaitoCorp.vendas.dto;

public class CriarCarrinhoDTO {
    private String idCliente;

    public CriarCarrinhoDTO() {}

    public CriarCarrinhoDTO(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }
}
