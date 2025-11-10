package com.projetoIntegrador.RaitoCorp.catalogo.model;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProdutoCategoriaId implements Serializable {

    private UUID produtoId;
    private UUID categoriaId;

    public ProdutoCategoriaId() {}

    public ProdutoCategoriaId(UUID produtoId, UUID categoriaId) {
        this.produtoId = produtoId;
        this.categoriaId = categoriaId;
    }

    public UUID getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(UUID produtoId) {
        this.produtoId = produtoId;
    }

    public UUID getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(UUID categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProdutoCategoriaId)) return false;
        ProdutoCategoriaId that = (ProdutoCategoriaId) o;
        return Objects.equals(produtoId, that.produtoId) &&
               Objects.equals(categoriaId, that.categoriaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId, categoriaId);
    }
}
