package com.projetoIntegrador.RaitoCorp.cadastro.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
public class UsuariosPerfisId implements Serializable {
    private UUID idUsuario;
    private UUID idPerfil;

    public UsuariosPerfisId() {}

    public UsuariosPerfisId(UUID idUsuario, UUID idPerfil) {
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
    }

    public UUID getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(UUID idUsuario) {
        this.idUsuario = idUsuario;
    }

    public UUID getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(UUID idPerfil) {
        this.idPerfil = idPerfil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuariosPerfisId)) return false;
        UsuariosPerfisId that = (UsuariosPerfisId) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
               Objects.equals(idPerfil, that.idPerfil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idPerfil);
    }
}
