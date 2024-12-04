package com.loja.hardwarestore.view;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.service.ProdutoService;
import javax.swing.*;
import java.awt.*;

public class TelaPagamentoCartao extends javax.swing.JFrame {

    public TelaPagamentoCartao() {
        initComponents();
    }

    private void initComponents() {
        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(0x004D40));

        JLabel lblTitulo = new JLabel("Pagamento com Cartão");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(new GridLayout(5, 2, 10, 10));
        painelFormulario.setBackground(new Color(0xA8D7B7));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNumeroCartao = new JLabel("Número do Cartão:");
        lblNumeroCartao.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNumeroCartao.setForeground(new Color(0x004D40));
        JTextField txtNumeroCartao = new JTextField();
        txtNumeroCartao.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel lblValidade = new JLabel("Validade (MM/AA):");
        lblValidade.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblValidade.setForeground(new Color(0x004D40));
        JTextField txtValidade = new JTextField();
        txtValidade.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel lblCodigoSeguranca = new JLabel("Código de Segurança:");
        lblCodigoSeguranca.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCodigoSeguranca.setForeground(new Color(0x004D40));
        JTextField txtCodigoSeguranca = new JTextField();
        txtCodigoSeguranca.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel lblNomeTitular = new JLabel("Nome do Titular:");
        lblNomeTitular.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNomeTitular.setForeground(new Color(0x004D40));
        JTextField txtNomeTitular = new JTextField();
        txtNomeTitular.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JLabel lblTotal = new JLabel("Total a Pagar: R$ " + calcularTotalCarrinho());
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTotal.setForeground(new Color(0x004D40));
        lblTotal.setHorizontalAlignment(SwingConstants.CENTER);

        painelFormulario.add(lblNumeroCartao);
        painelFormulario.add(txtNumeroCartao);
        painelFormulario.add(lblValidade);
        painelFormulario.add(txtValidade);
        painelFormulario.add(lblCodigoSeguranca);
        painelFormulario.add(txtCodigoSeguranca);
        painelFormulario.add(lblNomeTitular);
        painelFormulario.add(txtNomeTitular);
        painelFormulario.add(lblTotal);

        painelPrincipal.add(painelFormulario, BorderLayout.CENTER);

        JButton btnConfirmarPagamento = new JButton("Confirmar Pagamento");
        btnConfirmarPagamento.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirmarPagamento.setBackground(new Color(0x004D40));
        btnConfirmarPagamento.setForeground(Color.WHITE);
        btnConfirmarPagamento.setFocusPainted(false);
        btnConfirmarPagamento.setBorderPainted(false);
        btnConfirmarPagamento.setOpaque(true);
        btnConfirmarPagamento.addActionListener(e -> confirmarPagamento());

        painelPrincipal.add(btnConfirmarPagamento, BorderLayout.SOUTH);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento - Cartão de Crédito/Débito");
        getContentPane().add(painelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    private double calcularTotalCarrinho() {
        ProdutoService produtoService = new ProdutoService(new ProdutoDAO());
        return produtoService.calcularTotalCarrinho();
    }

    private void confirmarPagamento() {
        TelaCompraRealizada telaCompraRealizada = new TelaCompraRealizada();
        telaCompraRealizada.setVisible(true);

        this.dispose();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaPagamentoCartao().setVisible(true));
    }
}
