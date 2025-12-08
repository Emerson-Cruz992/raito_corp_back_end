package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

    List<Categoria> findByAtivoTrue();

    Optional<Categoria> findByNome(String nome);
}
