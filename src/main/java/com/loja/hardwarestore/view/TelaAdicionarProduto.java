package com.loja.hardwarestore.view;

import com.loja.hardwarestore.model.entidades.Produto;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class TelaAdicionarProduto extends JFrame {

    private JTextField campoNome, campoPreco, campoQuantidade;
    private JButton btnAdicionar, btnCancelar;

    public TelaAdicionarProduto() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Adicionar Produto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setAlignmentX(CENTER_ALIGNMENT);

        JLabel labelTitulo = new JLabel("Adicionar Novo Produto", SwingConstants.CENTER);
        painelConteudo.add(labelTitulo);
        painelConteudo.add(Box.createVerticalStrut(20));

        JLabel labelNome = new JLabel("Nome do Produto");
        campoNome = new JTextField(20);
        painelConteudo.add(labelNome);
        painelConteudo.add(campoNome);
        painelConteudo.add(Box.createVerticalStrut(10));

        JLabel labelPreco = new JLabel("Preço");
        campoPreco = new JTextField(20);
        painelConteudo.add(labelPreco);
        painelConteudo.add(campoPreco);
        painelConteudo.add(Box.createVerticalStrut(10));

        JLabel labelQuantidade = new JLabel("Quantidade");
        campoQuantidade = new JTextField(20);
        painelConteudo.add(labelQuantidade);
        painelConteudo.add(campoQuantidade);
        painelConteudo.add(Box.createVerticalStrut(20));

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.addActionListener(e -> adicionarProduto());
        painelConteudo.add(btnAdicionar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        painelConteudo.add(btnCancelar);

        setSize(400, 300);
        setLocationRelativeTo(null);
        add(painelConteudo);
    }

    private void adicionarProduto() {
        String nome = campoNome.getText();
        double preco;
        int quantidade;

        try {
            preco = Double.parseDouble(campoPreco.getText());
            quantidade = Integer.parseInt(campoQuantidade.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Preço ou quantidade inválidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Gera o ID automaticamente
        int id = gerarIdProduto();

        Produto produto = new Produto(id, nome, preco, quantidade);

        // Adiciona o produto ao arquivo produtos.txt
        try (FileWriter writer = new FileWriter("src/main/resources/produtos.txt", true)) {
            writer.write(produto.getId() + ";" + produto.getNome() + ";" + produto.getPreco() + ";" + produto.getQuantidade() + "\n");
            JOptionPane.showMessageDialog(this, "Produto adicionado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Fecha a tela
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao adicionar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private int gerarIdProduto() {
        List<Integer> ids = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/produtos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dadosProduto = linha.split(";");
                ids.add(Integer.parseInt(dadosProduto[0]));
            }
        } catch (IOException e) {
            // Caso o arquivo não exista, a lista de IDs será vazia
        }

        // Retorna o próximo ID disponível
        return ids.isEmpty() ? 1 : ids.stream().max(Integer::compareTo).get() + 1;
    }

    public static void main(String[] args) {
        new TelaAdicionarProduto().setVisible(true);
    }
}
