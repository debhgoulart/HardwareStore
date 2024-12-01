package com.loja.hardwarestore.dao;

import com.loja.hardwarestore.model.entidades.Produto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/produtos.txt";

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
                    e.printStackTrace(); // Opcional, exibe a pilha de erros
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return produtos;
    }

    public Produto processarLinha(String linha) {
        try {
            // Substitua "," por ";" se o delimitador for alterado no arquivo
            String[] partes = linha.split(";");

            if (partes.length != 4) { // Ajuste conforme o número de campos esperados
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
