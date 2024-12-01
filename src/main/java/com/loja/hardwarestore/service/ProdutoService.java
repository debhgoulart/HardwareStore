package com.loja.hardwarestore.service;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    // Método para obter todos os produtos
    public List<Produto> obterTodosProdutos() {
        return produtoDAO.obterTodosProdutos();
    }

    // Método para remover produto
    public boolean removerProduto(int idProduto) {
        return produtoDAO.removerProduto(idProduto);
    }
}
