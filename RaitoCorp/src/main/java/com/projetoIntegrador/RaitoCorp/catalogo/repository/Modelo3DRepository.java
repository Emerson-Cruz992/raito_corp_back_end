package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.Modelo3D;

public interface Modelo3DRepository extends JpaRepository<Modelo3D, UUID> {
    List<Modelo3D> findByProdutoId(UUID idProduto);
}
