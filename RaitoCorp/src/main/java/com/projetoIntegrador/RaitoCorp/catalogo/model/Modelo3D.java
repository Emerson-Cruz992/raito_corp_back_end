package com.projetoIntegrador.RaitoCorp.catalogo.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "modelos_3d")
public class Modelo3D {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String nomeArquivo;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private String formato;

    public Modelo3D() {}

    // ✅ usado pelo ProdutoService
    public Modelo3D(Produto produto, String nomeArquivo, String url, String formato) {
        this.produto = produto;
        this.nomeArquivo = nomeArquivo;
        this.url = url;
        this.formato = formato;
    }

    public UUID getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}
