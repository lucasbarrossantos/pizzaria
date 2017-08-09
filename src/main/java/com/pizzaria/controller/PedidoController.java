package com.pizzaria.controller;

import com.pizzaria.model.Pedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Pizzas;
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
    private Pizzas pizzas;

    @Autowired
    private TabelaItensPedido tabelaItensPedido;

    @GetMapping("/new")
    public ModelAndView novo(Pedido pedido){
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/item")
    public ModelAndView adicionarItem(Long codigoProduto){
        Produto produto = produtos.findOne(codigoProduto);
        tabelaItensPedido.adicionarItem(produto, 1);
        //System.out.printf(">> total de itens " + tabelaItensPedido.total());
        ModelAndView mv = new ModelAndView("pedido/TabelaItensProduto");
        mv.addObject("itens", tabelaItensPedido.getItens());
        return mv;
    }

    @PostMapping("/itemPizza")
    public ModelAndView adicionarItemPizza(Long codigoPizza){
        Pizza pizza = pizzas.findOne(codigoPizza);
        tabelaItensPedido.adicionarItemPizza(pizza, 1);
        ModelAndView mv = new ModelAndView("pedido/TabelaItensPizza");
        mv.addObject("itens", tabelaItensPedido.getItens());
        return mv;
    }

}
