package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import com.loja.hardwarestore.service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TelaCarrinho extends JFrame {

    private JTable tabelaCarrinho;
    private JScrollPane scrollPane;

    public TelaCarrinho() {
        initComponents();
        configurarTabela();
        carregarCarrinho();
    }

    private void configurarTabela() {
        if (tabelaCarrinho == null) {
            tabelaCarrinho = new JTable();
        }

        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Preço");
        tabelaCarrinho.setModel(modelo);

        tabelaCarrinho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaCarrinho.setRowHeight(40);
        tabelaCarrinho.setSelectionBackground(new Color(0x4CAF50));
        tabelaCarrinho.setSelectionForeground(Color.WHITE);

        if (scrollPane == null) {
            scrollPane = new JScrollPane(tabelaCarrinho);
        }

        scrollPane.setPreferredSize(new Dimension(800, 400));
    }

    private void carregarCarrinho() {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        ProdutoService produtoService = new ProdutoService(produtoDAO);

        List<Produto> produtos = produtoService.obterProdutosDoCarrinho();

        DefaultTableModel modelo = (DefaultTableModel) tabelaCarrinho.getModel();

        for (Produto produto : produtos) {
            Object[] linha = new Object[2];
            linha[0] = produto.getNome();
            linha[1] = "R$ " + String.format("%.2f", produto.getPreco());
            modelo.addRow(linha);
        }

        revalidate();
        repaint();
    }

    private void removerItemDoCarrinho() {
        int selectedRow = tabelaCarrinho.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel modelo = (DefaultTableModel) tabelaCarrinho.getModel();
            String nomeProduto = (String) modelo.getValueAt(selectedRow, 0);

            ProdutoService produtoService = new ProdutoService(new ProdutoDAO());

            boolean sucesso = produtoService.removerProdutoDoCarrinho(nomeProduto);

            if (sucesso) {
                modelo.removeRow(selectedRow);
                JOptionPane.showMessageDialog(this, "Produto removido do carrinho!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao remover produto do carrinho.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para remover.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void voltar() {
        TelaProdutos telaProdutos = new TelaProdutos();
        telaProdutos.setVisible(true);
        this.dispose();
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        JLabel jLabel1 = new JLabel("Hardware Store");
        JLabel jLabel2 = new JLabel("Carrinho:");
        JButton btnVoltar = new JButton("Voltar");
        JButton btnRemover = new JButton("Remover Item");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Carrinho de Compras");

        jLabel1.setFont(new Font("Segoe UI", Font.BOLD, 36));
        jLabel1.setForeground(new Color(0x00796B));

        jLabel2.setFont(new Font("Segoe UI", Font.BOLD, 18)); 
        jLabel2.setForeground(Color.WHITE);
        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(0x004D40));
        painelTopo.setLayout(new BoxLayout(painelTopo, BoxLayout.Y_AXIS));
        painelTopo.add(jLabel1);
        painelTopo.add(Box.createVerticalStrut(10));
        painelTopo.add(jLabel2);

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
        btnRemover.addActionListener(e -> removerItemDoCarrinho());

        JButton btnFormasPagamento = new JButton("Formas de Pagamento");
        btnFormasPagamento.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnFormasPagamento.setBackground(Color.WHITE);
        btnFormasPagamento.setForeground(new Color(0x004D40));
        btnFormasPagamento.setBorderPainted(false);
        btnFormasPagamento.setFocusPainted(false);
        btnFormasPagamento.addActionListener(e -> abrirTelaFormasPagamento());

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnFormasPagamento);

        painelBotoes.setBackground(new Color(0xE0F2F1));
        painelBotoes.add(btnVoltar);
        painelBotoes.add(btnRemover);

        configurarTabela();
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(painelTopo, BorderLayout.NORTH);
        getContentPane().add(painelBotoes, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
    }

    private void abrirTelaFormasPagamento() {
        TelaFormasPagamento telaFormasPagamento = new TelaFormasPagamento();
        telaFormasPagamento.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaCarrinho().setVisible(true));
    }
}
