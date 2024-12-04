package com.loja.hardwarestore.view;

import javax.swing.*;
import java.awt.*;

public class TelaFormasPagamento extends javax.swing.JFrame {

    private javax.swing.JButton btnCartao;
    private javax.swing.JButton btnPix;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel painelPrincipal;

    public TelaFormasPagamento() {
        initComponents();
    }

    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btnPix = new javax.swing.JButton();
        btnCartao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formas de Pagamento");

        painelPrincipal.setBackground(new Color(0x004D40));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        lblTitulo.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 26));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("Escolha a Forma de Pagamento");
        lblTitulo.setForeground(Color.WHITE);

        
        btnPix.setText("Pix");
        btnPix.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnPix.setBackground(new Color(178, 223, 219));
        btnPix.setForeground(new Color(0x004D40));
        btnPix.setFocusPainted(false);
        btnPix.setBorder(BorderFactory.createLineBorder(new Color(0x00796B), 2));
        btnPix.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnPix.addActionListener(evt -> btnPixActionPerformed(evt));

        btnCartao.setText("Cartão");
        btnCartao.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        btnCartao.setBackground(new Color(197, 225, 165));
        btnCartao.setForeground(new Color(0x33691E));
        btnCartao.setFocusPainted(false);
        btnCartao.setBorder(BorderFactory.createLineBorder(new Color(0x558B2F), 2));
        btnCartao.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCartao.addActionListener(evt -> btnCartaoActionPerformed(evt));

        painelPrincipal.setLayout(new BorderLayout(0, 30));
        painelPrincipal.add(lblTitulo, BorderLayout.NORTH);


        JPanel painelBotoes = new JPanel(new GridLayout(2, 1, 0, 20));
        painelBotoes.setOpaque(false);
        painelBotoes.add(btnPix);
        painelBotoes.add(btnCartao);

        painelPrincipal.add(painelBotoes, BorderLayout.CENTER);


        getContentPane().add(painelPrincipal, java.awt.BorderLayout.CENTER);


        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);
    }

    private void btnPixActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPagamentoPix telaPix = new TelaPagamentoPix();
        telaPix.setVisible(true);
    }

    private void btnCartaoActionPerformed(java.awt.event.ActionEvent evt) {
        TelaPagamentoCartao telaCartao = new TelaPagamentoCartao();
        telaCartao.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TelaFormasPagamento().setVisible(true));
    }
}
