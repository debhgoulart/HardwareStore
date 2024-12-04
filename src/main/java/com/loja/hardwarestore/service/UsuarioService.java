package com.loja.hardwarestore.service;

import com.loja.hardwarestore.dao.UsuarioDAO;
import com.loja.hardwarestore.model.dto.UsuarioDTO;
import com.loja.hardwarestore.model.entidades.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService() {
        this.usuarioDAO = new UsuarioDAO();
    }


    public boolean cadastrar(UsuarioDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioDAO.buscarUsuarioPorNome(usuarioDTO.getNome());
        if (usuarioExistente != null) {
            return false; 
        }

        Usuario novoUsuario = new Usuario(usuarioDTO.getNome(), usuarioDTO.getSenha());
        usuarioDAO.salvarUsuario(novoUsuario); 
        return true;
    }


    public boolean autenticar(String nome, String senha) {
        Usuario usuario = usuarioDAO.buscarUsuarioPorNome(nome);

        if (usuario != null && usuario.getSenha().equals(senha)) {
            return true;
        }

        return false;
    }
}
