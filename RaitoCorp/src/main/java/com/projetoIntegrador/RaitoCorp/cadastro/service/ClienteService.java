package com.projetoIntegrador.RaitoCorp.cadastro.service;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Cliente;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.ClienteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepo) {
        this.clienteRepo = clienteRepo;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepo.findAll();
    }

    public Optional<Cliente> buscarPorId(UUID id) {
        return clienteRepo.findById(id);
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        return clienteRepo.findByCpf(cpf);
    }

    public List<Cliente> buscarPorUsuario(UUID idUsuario) {
        return clienteRepo.findByIdUsuario(idUsuario);
    }

    public void deletarCliente(UUID id) {
        clienteRepo.deleteById(id);
    }
}
