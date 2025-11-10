package com.projetoIntegrador.RaitoCorp.cadastro.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetoIntegrador.RaitoCorp.cadastro.model.Credencial;
import com.projetoIntegrador.RaitoCorp.cadastro.repository.CredencialRepository;

@Service
public class CredencialService {

    private final CredencialRepository credencialRepo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

    public CredencialService(CredencialRepository credencialRepo) {
        this.credencialRepo = credencialRepo;
    }

    public Credencial criarCredencial(Credencial credencial) {
        String senhaCriptografada = encoder.encode(credencial.getSenhaHash());
        credencial.setSenhaHash(senhaCriptografada);
        return credencialRepo.save(credencial);
    }

    public boolean autenticar(String email, String senha) {
        Optional<Credencial> credencialOpt = credencialRepo.findByEmail(email);
        if (credencialOpt.isEmpty()) return false;

        Credencial credencial = credencialOpt.get();
        boolean senhaOk = encoder.matches(senha, credencial.getSenhaHash());

        if (senhaOk) {
            credencial.setUltimoLogin(LocalDateTime.now());
            credencial.setTentativasLogin(0);
            credencialRepo.save(credencial);
            return true;
        } else {
            credencial.setTentativasLogin(credencial.getTentativasLogin() + 1);
            credencialRepo.save(credencial);
            return false;
        }
    }
    public List<Credencial> listarcCredenciais() {
        return credencialRepo.findAll();
    }
}
