package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import com.loja.hardwarestore.service.ProdutoService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaRemoverProduto extends javax.swing.JFrame {

    private JTable tabelaProdutos;
    private JScrollPane scrollPane;

    public TelaRemoverProduto() {
        initComponents();
        configurarTabela();
        carregarProdutos();
    }

    private void configurarTabela() {
        if (tabelaProdutos == null) {
            tabelaProdutos = new JTable();
        }

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
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
            linha[0] = produto.getId();
            linha[1] = produto.getNome();
            linha[2] = "R$ " + String.format("%.2f", produto.getPreco());
            linha[3] = produto.getQuantidade();
            modelo.addRow(linha);
        }

        revalidate();
        repaint();
    }

    private void removerProduto() {
        int selectedRow = tabelaProdutos.getSelectedRow();
        if (selectedRow != -1) {
            int idProduto = (int) tabelaProdutos.getValueAt(selectedRow, 0);
            ProdutoDAO produtoDAO = new ProdutoDAO();
            ProdutoService produtoService = new ProdutoService(produtoDAO);

            boolean sucesso = produtoService.removerProduto(idProduto);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Produto removido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel modelo = (DefaultTableModel) tabelaProdutos.getModel();
                modelo.removeRow(selectedRow);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void voltar() {
        TelaProdutos telaProdutos = new TelaProdutos();
        telaProdutos.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JButton btnVoltar = new JButton("Voltar");
        JButton btnRemover = new JButton("Remover Produto");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Remover Produto");

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setText("Remover Produto");
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
        painelTopo.add(btnRemover);

        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(0x004D40));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> voltar());

        btnRemover.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnRemover.setBackground(Color.WHITE);
        btnRemover.setForeground(new Color(0x004D40));
        btnRemover.setBorderPainted(false);
        btnRemover.setFocusPainted(false);
        btnRemover.addActionListener(e -> removerProduto());

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
            java.util.logging.Logger.getLogger(TelaRemoverProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaRemoverProduto().setVisible(true);
            }
        });
    }

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
}
