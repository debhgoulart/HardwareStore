package com.loja.hardwarestore.dao;

import com.loja.hardwarestore.model.entidades.ItemCarrinho;
import com.loja.hardwarestore.model.entidades.Produto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CarrinhoDAO {

    private static final String ARQUIVO_CARRINHO = "src/main/resources/carrinho.txt";

    public void salvarCarrinho(List<ItemCarrinho> itens) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CARRINHO))) {
            for (ItemCarrinho item : itens) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ItemCarrinho> carregarCarrinho() {
        List<ItemCarrinho> itens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO_CARRINHO))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Exemplo de string: ProdutoX - R$ 100,00 x 2
                String[] parts = line.split(" - ");
                String nome = parts[0];
                double preco = Double.parseDouble(parts[1].substring(4).replace(",", "."));
                int quantidade = Integer.parseInt(parts[2].split(" x ")[1]);
                Produto produto = new Produto(0, nome, preco, 0); // Produto sem ID
                itens.add(new ItemCarrinho(produto, quantidade));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itens;
    }
}
