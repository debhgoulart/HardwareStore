package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import com.loja.hardwarestore.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TelaProdutos extends javax.swing.JFrame {

    private JTable tabelaProdutos;
    private JScrollPane scrollPane;

    public TelaProdutos() {
        initComponents();
        configurarTabela();
        carregarProdutos();
    }

    private void configurarTabela() {
        if (tabelaProdutos == null) {
            tabelaProdutos = new JTable();
        }

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        modelo.addColumn("Quantidade");

        tabelaProdutos.setModel(modelo);

        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaProdutos.setRowHeight(40);
        tabelaProdutos.setSelectionBackground(new Color(0x4CAF50));
        tabelaProdutos.setSelectionForeground(Color.WHITE);

        if (scrollPane == null) {
            scrollPane = new JScrollPane(tabelaProdutos);
        }

        scrollPane.setPreferredSize(new Dimension(800, 400));
    }

    private void carregarProdutos() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService produtoService = new ProdutoService(produtoDAO);

        List<Produto> produtos = produtoService.obterTodosProdutos();

        DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();

        for (Produto produto : produtos) {
            Object[] linha = new Object[4];
            linha[0] = produto.getNome();
            linha[1] = "R$ " + String.format("%.2f", produto.getPreco());
            linha[2] = produto.getQuantidade();
            modelo.addRow(linha);
        }

        revalidate();
        repaint();
    }

    private void adicionarAoCarrinho() {
    int selectedRow = tabelaProdutos.getSelectedRow();
    if (selectedRow != -1) {
        String nomeProduto = (String) tabelaProdutos.getValueAt(selectedRow, 0);
        String precoProdutoStr = (String) tabelaProdutos.getValueAt(selectedRow, 1);
        int quantidadeProduto = (int) tabelaProdutos.getValueAt(selectedRow, 2);

        precoProdutoStr = precoProdutoStr.replace("R$ ", "").replace(",", ".");
        double precoProduto = Double.parseDouble(precoProdutoStr);

        ProdutoService produtoService = new ProdutoService(new ProdutoDAO());
        produtoService.adicionarProdutoAoCarrinho(nomeProduto, precoProduto, quantidadeProduto);

        JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(this, "Selecione um produto para adicionar ao carrinho.", "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}

    private void visualizarCarrinho() {
        TelaCarrinho telaCarrinho = new TelaCarrinho();
        telaCarrinho.setVisible(true);
        this.dispose();
    }

    private void voltar() {
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        JLabel jLabel1 = new JLabel("Hardware Store");
        JLabel jLabel2 = new JLabel("Produtos:");
        JButton btnVoltar = new JButton("Voltar");
        JButton btnAdicionar = new JButton("Adicionar ao Carrinho");
        JButton btnVisualizarCarrinho = new JButton("Visualizar Carrinho");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adicionar ao Carrinho");

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(new Color(0x00796B));

        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel2.setForeground(new Color(0x0288D1));

        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(0x004D40));
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.add(jLabel1);
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(btnVoltar);
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(btnAdicionar);
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(btnVisualizarCarrinho);

        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(0x004D40));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> voltar());

        btnAdicionar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAdicionar.setBackground(Color.WHITE);
        btnAdicionar.setForeground(new Color(0x004D40));
        btnAdicionar.setBorderPainted(false);
        btnAdicionar.setFocusPainted(false);
        btnAdicionar.addActionListener(e -> adicionarAoCarrinho());

        btnVisualizarCarrinho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVisualizarCarrinho.setBackground(Color.WHITE);
        btnVisualizarCarrinho.setForeground(new Color(0x004D40));
        btnVisualizarCarrinho.setBorderPainted(false);
        btnVisualizarCarrinho.setFocusPainted(false);
        btnVisualizarCarrinho.addActionListener(e -> visualizarCarrinho());

        getContentPane().add(painelTopo, BorderLayout.NORTH);

        JPanel painelProdutos = new JPanel();
        painelProdutos.setBackground(new Color(0xB2DFDB));
        painelProdutos.add(jLabel2);
        getContentPane().add(painelProdutos, BorderLayout.CENTER);

        configurarTabela();
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel fundo = new JPanel();
        fundo.setBackground(new Color(0xE0F2F1));
        getContentPane().add(fundo, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaProdutos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new TelaProdutos().setVisible(true));
    }
}
