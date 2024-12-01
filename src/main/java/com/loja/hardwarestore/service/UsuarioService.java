package com.loja.hardwarestore.service;

import com.loja.hardwarestore.dao.UsuarioDAO;
import com.loja.hardwarestore.model.dto.UsuarioDTO;
import com.loja.hardwarestore.model.entidades.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }

    // Método para cadastrar o usuário
    public boolean cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorNome(usuarioDTO.getNome());
        if (usuarioExistente != null) {
            return false; // Usuário já existe
        }

        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getSenha());
        usuarioDAO.salvarUsuario(novoUsuario); // Salva o novo usuário
        return true;
    }

    // Método para autenticar o usuário
    public boolean autenticar(String nome, String senha) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(nome);

        // Verifica se o usuário existe e a senha está correta
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }

        return false;
    }
}
