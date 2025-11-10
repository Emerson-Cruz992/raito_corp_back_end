package com.projetoIntegrador.RaitoCorp.cadastro.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoIntegrador.RaitoCorp.cadastro.model.PerfilAcesso;
import com.projetoIntegrador.RaitoCorp.cadastro.model.Usuario;
import com.projetoIntegrador.RaitoCorp.cadastro.model.UsuariosPerfis;
import com.projetoIntegrador.RaitoCorp.cadastro.model.UsuariosPerfisId;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.PerfilAcessoRepository;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.UsuarioRepository;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.UsuariosPerfisRepository;

@Service
public class UsuarioPerfilService {

    private final UsuariosPerfisRepository usuariosPerfisRepository;
    private final PerfilAcessoRepository perfilAcessoRepository;
    private final UsuarioRepository usuarioRepository;

    public UsuarioPerfilService(UsuariosPerfisRepository usuariosPerfisRepository,
                                 PerfilAcessoRepository perfilAcessoRepository,
                                 UsuarioRepository usuarioRepository) {
        this.usuariosPerfisRepository = usuariosPerfisRepository;
        this.perfilAcessoRepository = perfilAcessoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    /** Vincula um perfil a um usuário **/
    @Transactional
    public UsuariosPerfis atribuirPerfil(UUID idUsuario, UUID idPerfil) {
        UsuariosPerfisId id = new UsuariosPerfisId(idUsuario, idPerfil);
        UsuariosPerfis up = new UsuariosPerfis();
        up.setId(id);
        return usuariosPerfisRepository.save(up);
    }

    /** Remove vínculo entre usuário e perfil **/
    @Transactional
    public void removerPerfil(UUID idUsuario, UUID idPerfil) {
        UsuariosPerfisId id = new UsuariosPerfisId(idUsuario, idPerfil);
        usuariosPerfisRepository.deleteById(id);
    }

    /** Lista os perfis associados a um usuário **/
    public List<PerfilAcesso> listarPerfisDetalhadosPorUsuario(UUID idUsuario) {
        List<UUID> idsPerfis = usuariosPerfisRepository.findAll().stream()
                .filter(up -> up.getId().getIdUsuario().equals(idUsuario))
                .map(up -> up.getId().getIdPerfil())
                .collect(Collectors.toList());

        return perfilAcessoRepository.findAllById(idsPerfis);
    }

    /** Lista todos os usuários que possuem um determinado perfil **/
    public List<Usuario> listarUsuariosPorPerfil(UUID idPerfil) {
        List<UUID> idsUsuarios = usuariosPerfisRepository.findAll().stream()
                .filter(up -> up.getId().getIdPerfil().equals(idPerfil))
                .map(up -> up.getId().getIdUsuario())
                .collect(Collectors.toList());

        return usuarioRepository.findAllById(idsUsuarios);
    }

    /** Lista todos os vínculos usuário <-> perfil **/
    public List<UsuariosPerfis> listarTodosVinculos() {
        return usuariosPerfisRepository.findAll();
    }
}
