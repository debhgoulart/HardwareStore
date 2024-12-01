package com.loja.hardwarestore.view;

import com.loja.hardwarestore.service.UsuarioService;

import javax.swing.*;
import java.awt.*;

public class TelaLogin extends JFrame {

    private JTextField campoNome;
    private JPasswordField campoSenha;
    private JButton btnLogin, btnCadastro;

    public TelaLogin() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Tela de Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações do layout da janela
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setAlignmentX(CENTER_ALIGNMENT);

        // Título
        JLabel labelTitulo = new JLabel("Bem-vindo à Loja", SwingConstants.CENTER);
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
        painelConteudo.add(Box.createVerticalStrut(20));

        // Botão Login
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> autenticarUsuario());
        painelConteudo.add(btnLogin);

        // Botão Cadastro
        btnCadastro = new JButton("Cadastrar");
        btnCadastro.addActionListener(e -> {
            new TelaCadastro().setVisible(true);
            dispose();
        });
        painelConteudo.add(btnCadastro);

        setSize(400, 300);
        setLocationRelativeTo(null);
        add(painelConteudo);
    }

    private void autenticarUsuario() {
        String nome = campoNome.getText();
        String senha = new String(campoSenha.getPassword());

        UsuarioService usuarioService = new UsuarioService();
        if (usuarioService.autenticar(nome, senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new TelaProdutos().setVisible(true); // Redireciona para a tela de produtos após login
            dispose(); // Fecha a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Nome de usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}
