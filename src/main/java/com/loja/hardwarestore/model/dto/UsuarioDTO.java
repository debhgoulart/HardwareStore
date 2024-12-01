package com.loja.hardwarestore.model.dto;

public class UsuarioDTO {
    private String nome;
    private String senha;

    public UsuarioDTO(String nome, String senha) {
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
