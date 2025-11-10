package com.projetoIntegrador.RaitoCorp.cadastro.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "UsuariosPerfis")
public class UsuariosPerfis implements Serializable {

    @EmbeddedId
    private UsuariosPerfisId id;

    public UsuariosPerfis() {}

    public UsuariosPerfis(UsuariosPerfisId id) {
        this.id = id;
    }

    public UsuariosPerfisId getId() {
        return id;
    }

    public void setId(UsuariosPerfisId id) {
        this.id = id;
    }

    public void setIdUsuario(java.util.UUID idUsuario) {
        if (this.id == null) this.id = new UsuariosPerfisId();
        this.id.setIdUsuario(idUsuario);
    }

    public void setIdPerfil(java.util.UUID idPerfil) {
        if (this.id == null) this.id = new UsuariosPerfisId();
        this.id.setIdPerfil(idPerfil);
    }

    public java.util.UUID getIdUsuario() {
        return this.id != null ? this.id.getIdUsuario() : null;
    }

    public java.util.UUID getIdPerfil() {
        return this.id != null ? this.id.getIdPerfil() : null;
    }
}
