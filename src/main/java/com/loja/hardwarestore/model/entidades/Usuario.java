package com.loja.hardwarestore.model.entidades;

public class Usuario {
    private String nome;
    private String senha;

    // Construtor
    public Usuario(String nome, String senha) {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        return senha;
    }
}
