package com.projetoIntegrador.RaitoCorp.catalogo.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos_categorias")
public class ProdutoCategoria implements Serializable {

    @EmbeddedId
    private ProdutoCategoriaId id = new ProdutoCategoriaId();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("produtoId") // mapeia o campo da classe ProdutoCategoriaId
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoriaId")
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public ProdutoCategoria() {}

    public ProdutoCategoria(Produto produto, Categoria categoria) {
        this.produto = produto;
        this.categoria = categoria;
        this.id = new ProdutoCategoriaId(produto.getId(), categoria.getId());
    }

    public ProdutoCategoriaId getId() {
        return id;
    }

    public void setId(ProdutoCategoriaId id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
