package com.projetoIntegrador.RaitoCorp.cadastro.dto;

public class LoginResponse {
    private String idUsuario;
    private String email;
    private String nome;
    private String token;

    public LoginResponse(String idUsuario, String email, String nome) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
        this.token = "dummy-token"; // Por enquanto token dummy até implementar JWT
    }

    // Getters e Setters
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
