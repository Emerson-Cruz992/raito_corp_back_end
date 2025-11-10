package com.projetoIntegrador.RaitoCorp.catalogo.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "imagens_produto")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    private boolean principal = false;

    public ImagemProduto() {}

    // ✅ usado pelo ProdutoService
    public ImagemProduto(Produto produto, String url, boolean principal) {
        this.produto = produto;
        this.url = url;
        this.principal = principal;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isPrincipal() {
        return principal;
    }

    public void setPrincipal(boolean principal) {
        this.principal = principal;
    }
}
