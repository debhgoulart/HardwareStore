package com.loja.hardwarestore.dao;

import com.loja.hardwarestore.model.entidades.Usuario;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private static final String ARQUIVO_USUARIOS = "src/main/resources/usuarios.txt";

    // Verifica e cria o arquivo se não existir
    public static void criarArquivoSeNaoExistir() {
        Path path = Paths.get(ARQUIVO_USUARIOS);
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Salva o usuário no arquivo
    public void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            writer.write(usuario.getNome() + ";" + usuario.getSenha() + "\n"); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lista os usuários no arquivo
    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_USUARIOS))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 2) { 
                    usuarios.add(new Usuario(dados[0], dados[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    // Busca usuário pelo nome
    public Usuario buscarUsuarioPorNome(String nome) {
        for (Usuario usuario : listarUsuarios()) {
            if (usuario.getNome().equals(nome)) {
                return usuario;
            }
        }
        return null;
    }
}
