package com.projetoIntegrador.RaitoCorp.catalogo.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "caracteristicas_iluminacao")
public class CaracteristicaIluminacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    @JsonIgnore
    private Produto produto;

    @Column(nullable = false)
    private String potencia;

    @Column(nullable = false)
    private String temperaturaCor;

    @Column(nullable = false)
    private String fluxoLuminoso;

    private String tensao;
    private String eficiencia;
    private String indiceProtecao;

    @Column(nullable = false)
    private boolean regulavel;

    public UUID getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getTemperaturaCor() {
        return temperaturaCor;
    }

    public void setTemperaturaCor(String temperaturaCor) {
        this.temperaturaCor = temperaturaCor;
    }

    public String getFluxoLuminoso() {
        return fluxoLuminoso;
    }

    public void setFluxoLuminoso(String fluxoLuminoso) {
        this.fluxoLuminoso = fluxoLuminoso;
    }

    public String getTensao() {
        return tensao;
    }

    public void setTensao(String tensao) {
        this.tensao = tensao;
    }

    public String getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(String eficiencia) {
        this.eficiencia = eficiencia;
    }

    public String getIndiceProtecao() {
        return indiceProtecao;
    }

    public void setIndiceProtecao(String indiceProtecao) {
        this.indiceProtecao = indiceProtecao;
    }

    public boolean isRegulavel() {
        return regulavel;
    }

    public void setRegulavel(boolean regulavel) {
        this.regulavel = regulavel;
    }
}
