package com.loja.hardwarestore.view;

import javax.swing.*;
import java.awt.*;

public class TelaCompraRealizada extends javax.swing.JFrame {

    public TelaCompraRealizada() {
        initComponents();
    }

    private void initComponents() {

        JPanel painelPrincipal = new JPanel();
        JLabel lblMensagem = new JLabel();
        JButton btnFechar = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Compra Realizada");

        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(0x004D40));

        lblMensagem.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblMensagem.setForeground(Color.WHITE);
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setText("Compra realizada com sucesso!");
        painelPrincipal.add(lblMensagem, BorderLayout.CENTER);

        btnFechar.setText("Fechar");
        btnFechar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnFechar.setBackground(new Color(0x4CAF50));
        btnFechar.setForeground(Color.WHITE);
        btnFechar.setFocusPainted(false);
        btnFechar.setBorderPainted(false);
        btnFechar.setOpaque(true);
        btnFechar.setPreferredSize(new Dimension(200, 50));
        btnFechar.addActionListener(evt -> btnFecharActionPerformed(evt));

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(new Color(0x004D40));
        painelBotao.add(btnFechar);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        getContentPane().add(painelPrincipal);

        setPreferredSize(new Dimension(500, 400));
        pack();
        setLocationRelativeTo(null);
    }

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaCompraRealizada().setVisible(true));
    }
}
