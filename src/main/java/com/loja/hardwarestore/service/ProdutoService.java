package com.loja.hardwarestore.service;

import com.loja.hardwarestore.dao.ProdutoDAO;
import com.loja.hardwarestore.model.entidades.Produto;
import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
    public List<Produto> obterTodosProdutos() {
        return produtoDAO.obterTodosProdutos();
    }

    public double calcularTotalCarrinho() {
        return produtoDAO.calcularTotalCarrinho();
    }

    public void adicionarProdutoAoCarrinho(String nomeProduto, double precoProduto, int quantidadeProduto) {
        produtoDAO.adicionarProdutoAoCarrinho(nomeProduto, precoProduto, quantidadeProduto);
    }

    public boolean adicionarProduto(Produto produto) {
        return produtoDAO.adicionarProduto(produto);
    }

    public boolean removerProduto(String nomeProduto) {
        return produtoDAO.removerProduto(nomeProduto);
    }

    public List<Produto> obterProdutosDoCarrinho() {
        return produtoDAO.obterProdutosDoCarrinho();
    }

    public boolean removerProdutoDoCarrinho(String nomeProduto) {
        return produtoDAO.removerProdutoDoCarrinho(nomeProduto);
    }

}
