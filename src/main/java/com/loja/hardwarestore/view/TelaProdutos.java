package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import com.loja.hardwarestore.service.ProdutoService;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class TelaProdutos extends javax.swing.JFrame {

    private JTable tabelaProdutos;
    private JScrollPane scrollPane;
    private List<Produto> produtosCarrinho = new ArrayList<>(); // Lista para os produtos no carrinho

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
        modelo.addColumn("Ação");

        tabelaProdutos.setModel(modelo);

        tabelaProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabelaProdutos.setRowHeight(40);
        tabelaProdutos.setSelectionBackground(new Color(0x4CAF50));
        tabelaProdutos.setSelectionForeground(Color.WHITE);

        tabelaProdutos.getColumn("Ação").setCellRenderer(new ButtonRenderer());
        tabelaProdutos.getColumn("Ação").setCellEditor(new ButtonEditor(new JCheckBox()));

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
            linha[3] = "Adicionar ao Carrinho";
            modelo.addRow(linha);
        }

        revalidate();
        repaint();
    }

    private void adicionarAoCarrinho(Produto produto) {
        if (produtosCarrinho == null) {
            produtosCarrinho = new ArrayList<>();  // Inicializa a lista se estiver null
        }
        produtosCarrinho.add(produto);
        String mensagem = "Produto " + produto.getNome() + " foi adicionado ao seu carrinho!";
        JOptionPane.showMessageDialog(this, mensagem, "Carrinho Atualizado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void visualizarCarrinho() {
        TelaCarrinho telaCarrinho = new TelaCarrinho();
        telaCarrinho.carregarCarrinho(produtosCarrinho);  // Passa a lista de produtos para TelaCarrinho
        telaCarrinho.setVisible(true);
        this.dispose(); // Fecha a tela de produtos
    }

    private void initComponents() {
        getContentPane().setLayout(new BorderLayout());

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JButton btnVoltar = new JButton("Voltar");
        JButton btnVerCarrinho = new JButton("Ver Carrinho");

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
        painelTopo.add(btnVerCarrinho);

        btnVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVoltar.setBackground(Color.WHITE);
        btnVoltar.setForeground(new Color(0x004D40));
        btnVoltar.setBorderPainted(false);
        btnVoltar.setFocusPainted(false);
        btnVoltar.addActionListener(e -> voltar());

        btnVerCarrinho.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btnVerCarrinho.setBackground(Color.WHITE);
        btnVerCarrinho.setForeground(new Color(0x004D40));
        btnVerCarrinho.setBorderPainted(false);
        btnVerCarrinho.setFocusPainted(false);
        btnVerCarrinho.addActionListener(e -> visualizarCarrinho());

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

    private void voltar() {
        // Cria e exibe a tela de login
        TelaLogin telaLogin = new TelaLogin();
        telaLogin.setVisible(true);
        // Fecha a tela de produtos atual
        this.dispose();
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

    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setText("Adicionar ao Carrinho");
            setBackground(Color.WHITE);
            setForeground(new Color(0x004D40));
            setFont(new Font("Segoe UI", Font.PLAIN, 14));
            setFocusPainted(false);
            setBorderPainted(false);
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private JButton button;
        private String label;
        private Produto produto;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setText("Adicionar ao Carrinho");
            button.setBackground(Color.WHITE);
            button.setForeground(new Color(0x004D40));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            button.setFocusPainted(false);
            button.setBorderPainted(false);
            button.setOpaque(true);
            button.addActionListener(e -> {
                int row = tabelaProdutos.getSelectedRow();
                String nome = (String) tabelaProdutos.getValueAt(row, 0);
                String precoStr = (String) tabelaProdutos.getValueAt(row, 1);
                precoStr = precoStr.replace("R$ ", "").replace(",", ".");
                double preco = Double.parseDouble(precoStr);
                int quantidade = (int) tabelaProdutos.getValueAt(row, 2);
                produto = new Produto(0, nome, preco, quantidade);
                adicionarAoCarrinho(produto);
            });
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }
    }
}
