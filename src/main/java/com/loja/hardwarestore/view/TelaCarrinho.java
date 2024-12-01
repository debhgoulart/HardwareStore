package com.loja.hardwarestore.view;

import com.loja.hardwarestore.model.entidades.Produto;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JFrame {

    private JPanel painelCarrinho;
    private JTextArea textAreaCarrinho;
    private JLabel labelCarrinhoVazio;
    private JButton btnVoltar;

    public TelaCarrinho() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Carrinho de Compras");

        // Define o layout e componentes
        painelCarrinho = new JPanel();
        painelCarrinho.setLayout(new BorderLayout());
        painelCarrinho.setBackground(new Color(0xE0F2F1));

        // Configuração do botão "Voltar"
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(0x004D40));
        btnVoltar.setFocusPainted(false);
        btnVoltar.setBorderPainted(false);
        btnVoltar.setPreferredSize(new Dimension(120, 40)); // Define um tamanho fixo para o botão
        btnVoltar.addActionListener(e -> voltar());

        // Configuração da label "Carrinho Vazio"
        labelCarrinhoVazio = new JLabel("<html><center>Seu carrinho está vazio.<br>Adicione produtos para continuar.</center></html>");
        labelCarrinhoVazio.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        labelCarrinhoVazio.setForeground(new Color(0x0288D1));
        labelCarrinhoVazio.setHorizontalAlignment(SwingConstants.CENTER);

        // Ajuste para exibir um texto simples quando não houver produtos
        textAreaCarrinho = new JTextArea();
        textAreaCarrinho.setEditable(false);
        textAreaCarrinho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        textAreaCarrinho.setBackground(new Color(0xB2DFDB));
        textAreaCarrinho.setVisible(false); // Escondido por padrão

        // Criação do JScrollPane para o JTextArea
        JScrollPane scrollPaneCarrinho = new JScrollPane(textAreaCarrinho);

        // Adicionando o botão "Voltar" no topo e os outros componentes no centro
        painelCarrinho.add(btnVoltar, BorderLayout.NORTH);  // Adiciona o botão "Voltar" ao topo
        painelCarrinho.add(labelCarrinhoVazio, BorderLayout.CENTER); // A label no centro
        painelCarrinho.add(scrollPaneCarrinho, BorderLayout.SOUTH); // O JScrollPane com o JTextArea (parte inferior)

        // Exibe o painel de carrinho
        add(painelCarrinho);
        setSize(1000, 600); // Tamanho da tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
    }

    // Ação do botão "Voltar"
    private void voltar() {
        TelaProdutos telaProdutos = new TelaProdutos();  // Cria uma nova instância da tela de produtos
        telaProdutos.setVisible(true); // Exibe a tela de produtos
        this.dispose(); // Fecha a tela atual de carrinho
    }

    public void carregarCarrinho(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            labelCarrinhoVazio.setVisible(true);
            textAreaCarrinho.setVisible(false); // Esconde o JTextArea
        } else {
            labelCarrinhoVazio.setVisible(false);
            textAreaCarrinho.setVisible(true); // Mostra o JTextArea
            StringBuilder carrinhoTexto = new StringBuilder("Produtos no Carrinho:\n\n");
            for (Produto produto : produtos) {
                carrinhoTexto.append(produto.getNome())
                        .append(" - R$ ")
                        .append(String.format("%.2f", produto.getPreco()))
                        .append("\n");
            }
            textAreaCarrinho.setText(carrinhoTexto.toString());
        }
    }
}
