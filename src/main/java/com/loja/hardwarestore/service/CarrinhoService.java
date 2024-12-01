package com.loja.hardwarestore.service;

import com.loja.hardwarestore.dao.CarrinhoDAO;
import com.loja.hardwarestore.model.entidades.ItemCarrinho;
import com.loja.hardwarestore.model.entidades.Produto;
import java.util.List;

public class CarrinhoService {

    private CarrinhoDAO carrinhoDAO;

    public CarrinhoService(CarrinhoDAO carrinhoDAO) {
        this.carrinhoDAO = carrinhoDAO;
    }

    public void adicionarItemCarrinho(List<ItemCarrinho> itensCarrinho, Produto produto, int quantidade) {
        // Verifica se o produto já existe no carrinho
        for (ItemCarrinho item : itensCarrinho) {
            if (item.getProduto().equals(produto)) {
                item.getProduto().setQuantidade(item.getQuantidade() + quantidade); // Atualiza a quantidade
                return;
            }
        }
        // Se o produto não existir, cria um novo item no carrinho
        itensCarrinho.add(new ItemCarrinho(produto, quantidade));
    }

    public void salvarCarrinho(List<ItemCarrinho> itensCarrinho) {
        carrinhoDAO.salvarCarrinho(itensCarrinho);
    }

    public List<ItemCarrinho> carregarCarrinho() {
        return carrinhoDAO.carregarCarrinho();
    }
}
