package com.projetoIntegrador.RaitoCorp.catalogo.model;

import java.util.UUID;

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
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String potencia; // Ex: "20W"

    @Column(nullable = false)
    private String temperaturaCor; // Ex: "3000K"

    @Column(nullable = false)
    private String fluxoLuminoso; // Ex: "2000lm"

    private String tensao; // Ex: "Bivolt", "127V"

    private String eficiencia; // Ex: "90lm/W"

    private String indiceProtecao; // Ex: "IP65"

    // =======================
    // Getters e Setters
    // =======================

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
}
