package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import com.loja.hardwarestore.service.ProdutoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaProdutosAdm extends javax.swing.JFrame {

    private JTable tabelaProdutos;
    private JScrollPane scrollPane;

    public TelaProdutosAdm() {
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
            Object[] linha = new Object[3];
            linha[0] = produto.getNome();
            linha[1] = "R$ " + String.format("%.2f", produto.getPreco());
            linha[2] = produto.getQuantidade();
            modelo.addRow(linha);
        }

        revalidate();
        repaint();
    }

    private void adicionarProduto() {
        // Exemplo de lógica para adicionar produto, você pode personalizar conforme necessário
        JOptionPane.showMessageDialog(this, "Tela para adicionar um novo produto!", "Adicionar Produto", JOptionPane.INFORMATION_MESSAGE);
        // Lógica para adicionar produto
    }

    private void removerProduto() {
        int linhaSelecionada = tabelaProdutos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            // Lógica para remover o produto selecionado
            DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
            modelo.removeRow(linhaSelecionada);
            JOptionPane.showMessageDialog(this, "Produto removido com sucesso!", "Produto Removido", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void voltar() {
        // Cria e exibe a tela de login
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        // Fecha a tela de produtos atual
        this.dispose();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JButton btnVoltar = new JButton("Voltar");
        JButton btnAdicionarProduto = new JButton("Adicionar Produto");
        JButton btnRemoverProduto = new JButton("Remover Produto");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Loja de Hardware");

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setText("Hardware Store");
        jLabel1.setForeground(new Color(0x00796B));

        jLabel2.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        jLabel2.setText("Produtos:");
        jLabel2.setForeground(new Color(0x0288D1));

        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(0x004D40));
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.add(jLabel1);
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(btnVoltar);
        painelTopo.add(Box.createVerticalStrut(10));

        // Adicionando os novos botões para adicionar e remover produto
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(btnAdicionarProduto);
        painelBotoes.add(btnRemoverProduto);

        painelTopo.add(painelBotoes);

        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(0x004D40));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> voltar());

        btnAdicionarProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnAdicionarProduto.setBackground(Color.WHITE);
        btnAdicionarProduto.setForeground(new Color(0x004D40));
        btnAdicionarProduto.setBorderPainted(false);
        btnAdicionarProduto.setFocusPainted(false);
        btnAdicionarProduto.addActionListener(e -> {
            TelaAdicionarProduto telaAdicionarProduto = new TelaAdicionarProduto();
            telaAdicionarProduto.setVisible(true);
        });

        btnRemoverProduto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRemoverProduto.setBackground(Color.WHITE);
        btnRemoverProduto.setForeground(new Color(0x004D40));
        btnRemoverProduto.setBorderPainted(false);
        btnRemoverProduto.setFocusPainted(false);
        btnRemoverProduto.addActionListener(e -> {
            TelaRemoverProduto telaRemoverProduto = new TelaRemoverProduto();
            telaRemoverProduto.setVisible(true);
        });

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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaProdutos().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
}
