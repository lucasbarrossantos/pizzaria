package com.pizzaria.controller;

import com.pizzaria.model.ItemPedido;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Produtos;
import com.pizzaria.service.PedidosService;
import com.pizzaria.session.TabelasItensSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private static final String CADASTRO = "pedido/CadastrarPedido";

    @Autowired
    private Produtos produtos;

    @Autowired
    private Pizzas pizzas;

    @Autowired
    private TabelasItensSession tabelaItens;

    @Autowired
    private PedidosService pedidosService;

    @GetMapping("/new")
    public ModelAndView novo(Pedido pedido) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        pedido.setUuid(UUID.randomUUID().toString());
        return mv;
    }

    @PostMapping("/item")
    public ModelAndView adicionarItem(Long codigoProduto, String uuid) {
        Produto produto = produtos.findOne(codigoProduto);
        tabelaItens.adicionarItem(uuid, produto, 1);
        //System.out.printf(">> total de itens " + tabelaItens.total());
        return mvTabelaItensProduto(uuid);
    }

    @PostMapping("/itemPizza")
    public ModelAndView adicionarItemPizza(Long codigoPizza, String uuid) {
        Pizza pizza = pizzas.findOne(codigoPizza);
        tabelaItens.adicionarItemPizza(uuid, pizza, 1);
        return mvTabelaItensPizza(uuid);
    }

    @PutMapping("/itemProduto/{codigoProduto}")
    public ModelAndView alterarQuantidadeItemProduto(@PathVariable("codigoProduto") Produto produto,
                                                     Integer quantidade, String uuid) {
        tabelaItens.alterarQuantidadeItensProduto(uuid, produto, quantidade);
        return mvTabelaItensProduto(uuid);
    }

    @PutMapping("/itemPizza/{codigoPizza}")
    public ModelAndView alterarQuantidadeItemPizza(@PathVariable("codigoPizza") Pizza pizza,
                                                   Integer quantidade, String uuid) {

        tabelaItens.alterarQuantidadeItensPizza(uuid, pizza, quantidade);
        return mvTabelaItensPizza(uuid);
    }

    @DeleteMapping("/itemProduto/{uuid}/{codigoProduto}")
    public ModelAndView excluirItemProduto(@PathVariable("codigoProduto") Produto produto, @PathVariable String uuid) {
        tabelaItens.excluirItemProduto(uuid, produto);
        return mvTabelaItensProduto(uuid);
    }

    @DeleteMapping("/itemPizza/{uuid}/{codigoPizza}")
    public ModelAndView excluirItemPizza(@PathVariable("codigoPizza") Pizza pizza, @PathVariable String uuid) {
        tabelaItens.excluirItemPizza(uuid, pizza);
        return mvTabelaItensPizza(uuid);
    }

    private ModelAndView mvTabelaItensProduto(String uuid) {
        ModelAndView mv = new ModelAndView("pedido/TabelaItensProduto");
        mv.addObject("itens", tabelaItens.getItensProdutos(uuid));
        mv.addObject("valorTotal", tabelaItens.getValorTotalProdutos(uuid));
        return mv;
    }

    private ModelAndView mvTabelaItensPizza(String uuid) {
        ModelAndView mv = new ModelAndView("pedido/TabelaItensPizza");
        mv.addObject("itens", tabelaItens.getItensPizzas(uuid));
        mv.addObject("valorTotal", tabelaItens.getValorTotalPizzas(uuid));
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Pedido pedido, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return novo(pedido);
        }

        pedido.setItens(tabelaItens.getItens(pedido.getUuid()));
        pedido.setValorTotal(tabelaItens.getItens(pedido.getUuid())
                .stream()
                .filter(i -> i.getValorTotal() != null)
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));

        pedido = pedidosService.salvar(pedido);
        attributes.addFlashAttribute("mensagem", "Pedido: " + pedido.getId() + " salvo com sucesso!");
        return new ModelAndView("redirect:/pedidos/new");
    }

}
