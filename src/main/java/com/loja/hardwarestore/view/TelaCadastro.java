package com.loja.hardwarestore.view;

import com.loja.hardwarestore.service.UsuarioService;
import com.loja.hardwarestore.model.dto.UsuarioDTO;

import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JFrame {

    private JTextField campoNome;
    private JPasswordField campoSenha, campoConfirmaSenha;
    private JButton btnCadastrar;

    public TelaCadastro() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Tela de Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações do layout da janela
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setAlignmentX(CENTER_ALIGNMENT);

        // Título
        JLabel labelTitulo = new JLabel("Crie sua Conta", SwingConstants.CENTER);
        painelConteudo.add(labelTitulo);
        painelConteudo.add(Box.createVerticalStrut(20));

        // Campo Nome
        JLabel labelNome = new JLabel("Nome de Usuário");
        campoNome = new JTextField(20);
        painelConteudo.add(labelNome);
        painelConteudo.add(campoNome);
        painelConteudo.add(Box.createVerticalStrut(10));

        // Campo Senha
        JLabel labelSenha = new JLabel("Senha");
        campoSenha = new JPasswordField(20);
        painelConteudo.add(labelSenha);
        painelConteudo.add(campoSenha);
        painelConteudo.add(Box.createVerticalStrut(10));

        // Campo Confirmar Senha
        JLabel labelConfirmaSenha = new JLabel("Confirmar Senha");
        campoConfirmaSenha = new JPasswordField(20);
        painelConteudo.add(labelConfirmaSenha);
        painelConteudo.add(campoConfirmaSenha);
        painelConteudo.add(Box.createVerticalStrut(20));

        // Botão Cadastrar
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(e -> cadastrarUsuario());
        painelConteudo.add(btnCadastrar);

        setSize(400, 300);
        setLocationRelativeTo(null);
        add(painelConteudo);
    }

    private void cadastrarUsuario() {
        String nome = campoNome.getText();
        String senha = new String(campoSenha.getPassword());
        String confirmaSenha = new String(campoConfirmaSenha.getPassword());

        if (!senha.equals(confirmaSenha)) {
            JOptionPane.showMessageDialog(this, "As senhas não coincidem!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTO usuarioDTO = new UsuarioDTO(nome, senha);

        if (usuarioService.cadastrar(usuarioDTO)) {
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new TelaLogin().setVisible(true); // Abre a tela de login após cadastro
            dispose(); // Fecha a tela de cadastro
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar usuário!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaCadastro().setVisible(true);
    }
}
