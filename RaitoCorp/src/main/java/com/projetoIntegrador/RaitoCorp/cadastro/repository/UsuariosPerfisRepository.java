package com.projetoIntegrador.RaitoCorp.cadastro.repository;

import com.projetoIntegrador.RaitoCorp.cadastro.model.UsuariosPerfis;
import com.projetoIntegrador.RaitoCorp.cadastro.model.UsuariosPerfisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosPerfisRepository extends JpaRepository<UsuariosPerfis, UsuariosPerfisId> {
}
