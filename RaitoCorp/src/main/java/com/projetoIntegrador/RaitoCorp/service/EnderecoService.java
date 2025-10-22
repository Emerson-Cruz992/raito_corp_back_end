package com.projetoIntegrador.RaitoCorp.service;

import com.projetoIntegrador.RaitoCorp.model.Endereco;
import com.projetoIntegrador.RaitoCorp.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepo;

    public EnderecoService(EnderecoRepository enderecoRepo) {
        this.enderecoRepo = enderecoRepo;
    }

    public Endereco criarEndereco(Endereco endereco) {
        return enderecoRepo.save(endereco);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepo.findAll();
    }

    public List<Endereco> listarPorCliente(UUID idCliente) {
        return enderecoRepo.findByIdCliente(idCliente);
    }

    public Optional<Endereco> buscarPorId(UUID idEndereco) {
        return enderecoRepo.findById(idEndereco);
    }

    public void deletarEndereco(UUID idEndereco) {
        enderecoRepo.deleteById(idEndereco);
    }
}
