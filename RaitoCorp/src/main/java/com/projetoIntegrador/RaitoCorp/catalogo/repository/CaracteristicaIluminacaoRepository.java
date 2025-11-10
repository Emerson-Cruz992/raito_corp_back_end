package com.projetoIntegrador.RaitoCorp.catalogo.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetoIntegrador.RaitoCorp.catalogo.model.CaracteristicaIluminacao;

public interface CaracteristicaIluminacaoRepository extends JpaRepository<CaracteristicaIluminacao, UUID> {
    List<CaracteristicaIluminacao> findByProdutoId(UUID idProduto);
}
