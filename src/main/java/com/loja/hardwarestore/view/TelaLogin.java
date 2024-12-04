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

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel labelTitulo = new JLabel("Bem-vindo à HardwareStore", SwingConstants.CENTER);
        painelConteudo.add(labelTitulo);
        painelConteudo.add(Box.createVerticalStrut(20));

        JLabel labelNome = new JLabel("Nome de Usuário");
        campoNome = new JTextField(20);
        painelConteudo.add(labelNome);
        painelConteudo.add(campoNome);
        painelConteudo.add(Box.createVerticalStrut(10));

        //senha
        JLabel labelSenha = new JLabel("Senha");
        campoSenha = new JPasswordField(20);
        painelConteudo.add(labelSenha);
        painelConteudo.add(campoSenha);
        painelConteudo.add(Box.createVerticalStrut(20));

        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> autenticarUsuario());
        painelConteudo.add(btnLogin);
        
        painelConteudo.add(Box.createVerticalStrut(10));

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

        //é adm?
        if (nome.equals("adm") && senha.equals("adm")) {
            JOptionPane.showMessageDialog(this, "Login como Administrador!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            new TelaProdutosAdm().setVisible(true);
        } else {
            UsuarioService usuarioService = new UsuarioService();
            if (usuarioService.autenticar(nome, senha)) {
                JOptionPane.showMessageDialog(this, "Login bem-sucedido!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                new TelaProdutos().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Nome de usuário ou senha incorretos!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }

        dispose();
    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }
}
