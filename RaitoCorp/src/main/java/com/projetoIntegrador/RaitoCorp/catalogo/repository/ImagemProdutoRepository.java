package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.projetoIntegrador.RaitoCorp.catalogo.model.ImagemProduto;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, UUID> {

    List<ImagemProduto> findByProdutoId(UUID idProduto);

    @Transactional
    @Modifying
    @Query("UPDATE ImagemProduto i SET i.principal = false WHERE i.produto.id = :idProduto")
    void desmarcarImagensPrincipais(UUID idProduto);
}
