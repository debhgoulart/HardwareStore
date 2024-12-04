package com.loja.hardwarestore.dao;

import com.loja.hardwarestore.model.entidades.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private static final String CAMINHO_ARQUIVO = "src/main/resources/produtos.txt";

    public List<Produto> carregarProdutosCarrinho() {
        List<Produto> produtosCarrinho = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/carrinho.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    Produto produto = processarLinha(linha);
                    produtosCarrinho.add(produto);
                } catch (IllegalArgumentException e) {
                    System.err.println("Erro ao processar linha do carrinho: " + linha);
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo do carrinho: " + e.getMessage());
        }

        return produtosCarrinho;
    }

    public void adicionarProdutoAoCarrinho(String nomeProduto, double precoProduto, int quantidadeProduto) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/carrinho.txt", true))) {
            writer.write(nomeProduto + ";" + precoProduto + ";" + quantidadeProduto);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao adicionar produto no carrinho: " + e.getMessage());
        }
    }

    public boolean adicionarProduto(Produto produto) {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO, true)) {
            writer.write(produto.getNome() + ";" + produto.getPreco() + ";" + produto.getQuantidade() + "\n");
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao adicionar produto: " + e.getMessage());
            return false;
        }
    }

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

    public double calcularTotalCarrinho() {
        double total = 0;
        List<Produto> produtosCarrinho = carregarProdutosCarrinho();

        for (Produto produto : produtosCarrinho) {
            total += produto.getPreco();
        }

        return total;
    }

    public List<Produto> obterProdutosDoCarrinho() {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/carrinho.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 3) {
                    String nome = partes[0];
                    double preco = Double.parseDouble(partes[1]);
                    int quantidade = Integer.parseInt(partes[2]);
                    produtos.add(new Produto(nome, preco, quantidade));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
        return produtos;
    }

    public List<Produto> carregarProdutosDoArquivo() {
        List<Produto> produtos = new ArrayList<>();
        String caminhoArquivo = "src/main/resources/produtos.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    String nome = dados[0];
                    double preco = Double.parseDouble(dados[1]);
                    int quantidade = Integer.parseInt(dados[2]);
                    produtos.add(new Produto(nome, preco, quantidade));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public boolean removerProduto(String nomeProduto) {
        List<Produto> produtos = obterTodosProdutos();
        boolean produtoRemovido = false;

        // Remover o produto correspondente
        for (Produto produto : produtos) {
            if (produto.getNome().equals(nomeProduto)) {
                produtos.remove(produto);
                produtoRemovido = true;
                break;
            }
        }

        // Se o produto foi removido, reescrever o arquivo sem ele
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

    private String formatarProduto(Produto produto) {
        // Corrigir formatação do produto
        return produto.getNome() + ";" + produto.getPreco() + ";" + produto.getQuantidade();
    }

    public Produto processarLinha(String linha) {
        try {
            String[] partes = linha.split(";");

            if (partes.length != 3) {
                throw new IllegalArgumentException("Formato da linha inválido: " + linha);
            }

            String nome = partes[0].trim();
            double preco = Double.parseDouble(partes[1].trim());
            int quantidade = Integer.parseInt(partes[2].trim());

            return new Produto(nome, preco, quantidade);
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato da linha inválido: " + linha, e);
        }
    }

    public boolean removerProdutoDoCarrinho(String nomeProduto) {
        List<Produto> produtosCarrinho = carregarProdutosCarrinho();
        boolean produtoRemovido = produtosCarrinho.removeIf(produto -> produto.getNome().equals(nomeProduto));

        if (produtoRemovido) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/carrinho.txt"))) {
                for (Produto produto : produtosCarrinho) {
                    writer.write(formatarProduto(produto));
                    writer.newLine();
                }
                return true;
            } catch (IOException e) {
                System.err.println("Erro ao atualizar o carrinho: " + e.getMessage());
            }
        }

        return false;
    }

}
