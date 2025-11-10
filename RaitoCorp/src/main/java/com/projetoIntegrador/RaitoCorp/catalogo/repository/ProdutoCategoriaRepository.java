package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoria;
import com.projetoIntegrador.RaitoCorp.catalogo.model.ProdutoCategoriaId;

public interface ProdutoCategoriaRepository extends JpaRepository<ProdutoCategoria, ProdutoCategoriaId> {
    List<ProdutoCategoria> findByProdutoId(UUID produtoId);
}
