package com.projetoIntegrador.RaitoCorp.vendas.dto;

public class AtualizarStatusPedidoDTO {
    private String status;

    public AtualizarStatusPedidoDTO() {}

    public AtualizarStatusPedidoDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
