package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    // Lista apenas as categorias com ativo = true
    List<Categoria> findByAtivoTrue();

    // Caso queira buscar também por parâmetro boolean
    List<Categoria> findByAtivo(boolean ativo);
}
