package com.loja.hardwarestore.dao;

import com.loja.hardwarestore.model.entidades.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/produtos.txt";

    // Método para obter todos os produtos
    public List<Produto> obterTodosProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMINHO_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    Produto produto = processarLinha(linha);
                    produtos.add(produto);
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao processar linha: " + linha);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return produtos;
    }

    // Método para remover um produto
    public boolean removerProduto(int idProduto) {
        List<Produto> produtos = obterTodosProdutos();
        boolean produtoRemovido = false;

        // Remover o produto com o ID fornecido
        for (Produto produto : produtos) {
            if (produto.getId() == idProduto) {
                produtos.remove(produto);
                produtoRemovido = true;
                break;
            }
        }

        // Se algum produto foi removido, regravar o arquivo
        if (produtoRemovido) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
                for (Produto produto : produtos) {
                    writer.write(formatarProduto(produto));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao gravar o arquivo: " + e.getMessage());
            }
        }

        return false;
    }

    // Método auxiliar para formatar produto para salvar no arquivo
    private String formatarProduto(Produto produto) {
        return produto.getId() + ";" + produto.getNome() + ";" + produto.getPreco() + ";" + produto.getQuantidade();
    }

    // Método para processar a linha do arquivo
    public Produto processarLinha(String linha) {
        try {
            String[] partes = linha.split(";");

            if (partes.length != 4) {
                throw new IllegalArgumentException("Formato da linha inválido: " + linha);
            }

            int id = Integer.parseInt(partes[0].trim());
            String nome = partes[1].trim();
            double preco = Double.parseDouble(partes[2].trim());
            int quantidade = Integer.parseInt(partes[3].trim());

            return new Produto(id, nome, preco, quantidade);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato da linha inválido: " + linha, e);
        }
    }
}
