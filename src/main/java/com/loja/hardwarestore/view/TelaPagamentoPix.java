package com.loja.hardwarestore.view;

import javax.swing.*;
import java.awt.*;

public class TelaPagamentoPix extends javax.swing.JFrame {

    private javax.swing.JLabel lblMensagem;
    private javax.swing.JLabel lblQRCode;
    private javax.swing.JPanel painelPrincipal;
    private javax.swing.JButton btnConfirmar;

    public TelaPagamentoPix() {
        initComponents();
    }

    private void initComponents() {

        painelPrincipal = new javax.swing.JPanel();
        lblMensagem = new javax.swing.JLabel();
        lblQRCode = new javax.swing.JLabel();
        btnConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pagamento com Pix");

        // Configuração do painel principal
        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(0xE0F2F1));

        // Configuração do título/mensagem
        lblMensagem.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblMensagem.setForeground(new Color(0x004D40));
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setText("Aqui está seu QR Code:");
        painelPrincipal.add(lblMensagem, BorderLayout.NORTH);

        // Configuração do QR Code
        lblQRCode.setHorizontalAlignment(SwingConstants.CENTER);
        lblQRCode.setText("[QR CODE]");
        lblQRCode.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        lblQRCode.setForeground(new Color(0x004D40));
        lblQRCode.setBorder(BorderFactory.createLineBorder(new Color(0x004D40), 2));
        lblQRCode.setOpaque(true);
        lblQRCode.setBackground(Color.WHITE);
        lblQRCode.setPreferredSize(new Dimension(200, 200));

        JPanel painelQRCode = new JPanel();
        painelQRCode.setBackground(new Color(0xB2DFDB));
        painelQRCode.add(lblQRCode);
        painelPrincipal.add(painelQRCode, BorderLayout.CENTER);

        // Configuração do botão
        btnConfirmar.setText("Confirmar");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnConfirmar.setBackground(new Color(0x4CAF50));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFocusPainted(false);
        btnConfirmar.setBorderPainted(false);
        btnConfirmar.setOpaque(true);
        btnConfirmar.setPreferredSize(new Dimension(200, 50));
        btnConfirmar.addActionListener(evt -> btnConfirmarActionPerformed(evt));

        JPanel painelBotao = new JPanel();
        painelBotao.setBackground(new Color(0xE0F2F1));
        painelBotao.add(btnConfirmar);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        // Adicionar painel principal ao frame
        getContentPane().add(painelPrincipal);

        // Configuração do JFrame
        setPreferredSize(new Dimension(500, 500));
        pack();
        setLocationRelativeTo(null);
    }

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this, "Pagamento confirmado via Pix!", "Confirmação", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaPagamentoPix().setVisible(true));
    }
}
