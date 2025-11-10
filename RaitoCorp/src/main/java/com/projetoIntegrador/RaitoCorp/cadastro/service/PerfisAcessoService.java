package com.projetoIntegrador.RaitoCorp.cadastro.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.cadastro.model.PerfilAcesso;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.PerfilAcessoRepository;

@Service
public class PerfisAcessoService {

    private final PerfilAcessoRepository repository;

    public PerfisAcessoService(PerfilAcessoRepository repository) {
        this.repository = repository;
    }

    public List<PerfilAcesso> listarTodos() {
        return repository.findAll();
    }

    public PerfilAcesso criar(PerfilAcesso perfil) {
        return repository.save(perfil);
    }

    public PerfilAcesso atualizar(UUID id, PerfilAcesso perfilAtualizado) {
        return repository.findById(id).map(perfil -> {
            perfil.setNome(perfilAtualizado.getNome());
            perfil.setDescricao(perfilAtualizado.getDescricao());
            return repository.save(perfil);
        }).orElseThrow(() -> new RuntimeException("Perfil não encontrado"));
    }

    public void excluir(UUID id) {
        repository.deleteById(id);
    }
}
