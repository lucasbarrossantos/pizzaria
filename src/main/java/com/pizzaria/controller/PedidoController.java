package com.pizzaria.controller;

import com.pizzaria.model.Pedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Produtos;
import com.pizzaria.session.TabelaItensPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
    public ModelAndView novo(Pedido pedido) {
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/item")
    public ModelAndView adicionarItem(Long codigoProduto) {
        Produto produto = produtos.findOne(codigoProduto);
        tabelaItensPedido.adicionarItem(produto, 1);
        //System.out.printf(">> total de itens " + tabelaItensPedido.total());
        return mvTabelaItensProduto();
    }

    @PostMapping("/itemPizza")
    public ModelAndView adicionarItemPizza(Long codigoPizza) {
        Pizza pizza = pizzas.findOne(codigoPizza);
        tabelaItensPedido.adicionarItemPizza(pizza, 1);
        return mvTabelaItensPizza();
    }

    @PutMapping("/itemProduto/{codigoProduto}")
    public ModelAndView alterarQuantidadeItemProduto(@PathVariable("codigoProduto") Produto produto,
                                                     Integer quantidade) {
        tabelaItensPedido.alterarQuantidadeItensProduto(produto, quantidade);
        return mvTabelaItensProduto();
    }

    @PutMapping("/itemPizza/{codigoPizza}")
    public ModelAndView alterarQuantidadeItemPizza(@PathVariable("codigoPizza") Pizza pizza,
                                                   Integer quantidade) {

        tabelaItensPedido.alterarQuantidadeItensPizza(pizza, quantidade);
        return mvTabelaItensPizza();
    }

    @DeleteMapping("/itemProduto/{codigoProduto}")
    public ModelAndView excluirItemProduto(@PathVariable("codigoProduto") Produto produto){
        tabelaItensPedido.excluirItemProduto(produto);
        return mvTabelaItensProduto();
    }

    @DeleteMapping("/itemPizza/{codigoPizza}")
    public ModelAndView excluirItemPizza(@PathVariable("codigoPizza") Pizza pizza){
        tabelaItensPedido.excluirItemPizza(pizza);
        return mvTabelaItensPizza();
    }

    private ModelAndView mvTabelaItensProduto() {
        ModelAndView mv = new ModelAndView("pedido/TabelaItensProduto");
        mv.addObject("itens", tabelaItensPedido.getItensProdutos());
        return mv;
    }

    private ModelAndView mvTabelaItensPizza() {
        ModelAndView mv = new ModelAndView("pedido/TabelaItensPizza");
        mv.addObject("itens", tabelaItensPedido.getItensPizzas());
        return mv;
    }

}
