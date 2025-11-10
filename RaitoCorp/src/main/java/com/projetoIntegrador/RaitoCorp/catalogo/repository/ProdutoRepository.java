package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);
    List<Produto> findByAtivoTrue();
}
