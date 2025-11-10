package com.projetoIntegrador.RaitoCorp.cadastro.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PerfisAcesso")
public class PerfilAcesso {

    @Id
    @GeneratedValue
    @Column(name = "id_perfil", columnDefinition = "UUID")
    private UUID idPerfil;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    public PerfilAcesso() {}

    public PerfilAcesso(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public UUID getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(UUID idPerfil) {
        this.idPerfil = idPerfil;
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
}
