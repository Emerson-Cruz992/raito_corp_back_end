package com.projetoIntegrador.RaitoCorp.vendas.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoAdminDTO {
    private String idPedido;
    private String nomeCliente;
    private String emailCliente;
    private BigDecimal valorTotal;
    private String status;
    private LocalDateTime criadoEm;
    private Integer quantidadeItens;

    public PedidoAdminDTO() {}

    public PedidoAdminDTO(String idPedido, String nomeCliente, String emailCliente,
                         BigDecimal valorTotal, String status, LocalDateTime criadoEm,
                         Integer quantidadeItens) {
        this.idPedido = idPedido;
        this.nomeCliente = nomeCliente;
        this.emailCliente = emailCliente;
        this.valorTotal = valorTotal;
        this.status = status;
        this.criadoEm = criadoEm;
        this.quantidadeItens = quantidadeItens;
    }

    // Getters e Setters
    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Integer getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(Integer quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }
}
