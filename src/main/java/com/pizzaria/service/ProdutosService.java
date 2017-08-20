package com.pizzaria.service;

import com.pizzaria.model.Produto;
import com.pizzaria.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    @Autowired
    private Produtos produtos;

    public Produto salvar(Produto produto){
        return produtos.saveAndFlush(produto);
    }

    public void excluir(Produto produto) {
        try {
            produtos.delete(produto);
            produtos.flush();
        }catch (RuntimeException e){
            throw new RuntimeException("Impossível apagar o produto. " + produto.getId());
        }
    }
}
