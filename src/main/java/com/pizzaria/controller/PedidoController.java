package com.pizzaria.controller;

import com.pizzaria.model.Pedido;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Produtos;
import com.pizzaria.session.TabelaItensPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private static final String CADASTRO = "pedido/CadastrarPedido";

    @Autowired
    private Produtos produtos;

    @Autowired
    private TabelaItensPedido tabelaItensPedido;

    @GetMapping("/new")
    public ModelAndView novo(Pedido pedido){
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/item")
    public @ResponseBody String adicionarItem(Long codigoProduto){
        Produto produto = produtos.findOne(codigoProduto);
        tabelaItensPedido.adicionarItem(produto, 1);
        System.out.printf(">> total de itens " + tabelaItensPedido.total());
        return "Item adicionado";
    }

}
