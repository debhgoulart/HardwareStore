package com.loja.hardwarestore.view;

import com.loja.hardwarestore.model.entidades.Produto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class TelaCarrinho extends JFrame {

    private List<Produto> carrinho;

    // Construtor que recebe o carrinho de produtos
    public TelaCarrinho(List<Produto> carrinho) {
        this.carrinho = carrinho; // Atribui o carrinho passado ao campo
        initComponents();
    }

    private void initComponents() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(0xA5D6A7)); // Verde pastel para o fundo

        // Título
        JLabel lblTitulo = new JLabel("Carrinho de Compras");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(new Color(0x00796B)); // Cor similar ao título da tela de produtos
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        // Painel para itens do carrinho
        JTextArea areaItens = new JTextArea();
        areaItens.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        areaItens.setEditable(false);
        areaItens.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0x0288D1), 2)); // Cor da borda semelhante

        // Adiciona os produtos do carrinho na área de texto
        if (carrinho.isEmpty()) {
            areaItens.setText("Carrinho vazio");
        } else {
            StringBuilder itens = new StringBuilder("Itens no carrinho:\n");
            for (Produto produto : carrinho) {
                itens.append("- ").append(produto.getNome()).append(" - R$ ").append(String.format("%.2f", produto.getPreco())).append("\n");
            }
            areaItens.setText(itens.toString());
        }

        JScrollPane scrollPane = new JScrollPane(areaItens);
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);

        // Botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setBackground(new Color(0xE0F2F1)); // Cor de fundo dos botões mais suave
        JButton btnComprar = new JButton("Comprar");
        btnComprar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnComprar.setBackground(new Color(0x4CAF50)); // Cor verde similar ao da tela de produtos
        btnComprar.setForeground(Color.WHITE);
        btnComprar.addActionListener(e -> comprarItens());

        JButton btnLimpar = new JButton("Limpar Carrinho");
        btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnLimpar.setBackground(new Color(0xF44336)); // Cor vermelha similar ao da tela de produtos
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.addActionListener(e -> limparCarrinho(areaItens));

        painelBotoes.add(btnComprar);
        painelBotoes.add(btnLimpar);

        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        // Configurações da janela
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carrinho de Compras");
        getContentPane().add(painelPrincipal);
        setSize(400, 600);
        setLocationRelativeTo(null);
    }

    private void comprarItens() {
        JOptionPane.showMessageDialog(this, "Compra realizada com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    private void limparCarrinho(JTextArea areaItens) {
        carrinho.clear();
        areaItens.setText("Carrinho vazio");
        // Limpa o arquivo carrinho.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/carrinho.txt"))) {
            writer.write(""); // Limpa o conteúdo do arquivo
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao limpar o carrinho!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
        JOptionPane.showMessageDialog(this, "Carrinho limpo com sucesso!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaCarrinho(List.of()).setVisible(true)); // Exemplo sem produtos
    }
}
