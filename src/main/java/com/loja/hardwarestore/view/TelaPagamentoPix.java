package com.loja.hardwarestore.view;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

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

        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.setBackground(new Color(0x004D40));

        lblMensagem.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblMensagem.setForeground(Color.WHITE);
        lblMensagem.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensagem.setText("Copie a chave PIX:");
        painelPrincipal.add(lblMensagem, BorderLayout.NORTH);

        lblQRCode.setHorizontalAlignment(SwingConstants.CENTER);
        lblQRCode.setText(generatePixCode());
        lblQRCode.setFont(new Font("Segoe UI", Font.ITALIC, 16));
        lblQRCode.setForeground(new Color(0x004D40));
        lblQRCode.setBorder(BorderFactory.createLineBorder(new Color(0x004D40), 2));
        lblQRCode.setOpaque(true);
        lblQRCode.setBackground(Color.WHITE);

        lblQRCode.setPreferredSize(new Dimension(600, 200));
        lblQRCode.setFont(new Font("Segoe UI", Font.BOLD, 24));

        JPanel painelQRCode = new JPanel();
        painelQRCode.setBackground(new Color(0xA8D7B7));
        painelQRCode.setLayout(new BorderLayout());
        painelQRCode.add(lblQRCode, BorderLayout.CENTER);
        painelPrincipal.add(painelQRCode, BorderLayout.CENTER);

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
        painelBotao.setBackground(new Color(0x004D40));
        painelBotao.add(btnConfirmar);
        painelPrincipal.add(painelBotao, BorderLayout.SOUTH);

        getContentPane().add(painelPrincipal);

        setPreferredSize(new Dimension(800, 700));
        pack();
        setLocationRelativeTo(null);
    }

    private String generatePixCode() {
        StringBuilder pixCode = new StringBuilder();
        
        while (pixCode.length() < 44) {
            String uuidPart = UUID.randomUUID().toString().replace("-", "");
            pixCode.append(uuidPart);
        }
        
        return pixCode.substring(0, 44);
    }

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {
        TelaCompraRealizada telaCompra = new TelaCompraRealizada();
        telaCompra.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new TelaPagamentoPix().setVisible(true));
    }
}
