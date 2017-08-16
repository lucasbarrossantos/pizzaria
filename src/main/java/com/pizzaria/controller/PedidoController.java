package com.pizzaria.controller;

import com.pizzaria.model.ItemPedido;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Pedidos;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Produtos;
import com.pizzaria.service.PedidosService;
import com.pizzaria.session.TabelasItensSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private Pedidos pedidos;

    @GetMapping("/new")
    public ModelAndView novo(Pedido pedido) {
        ModelAndView mv = new ModelAndView(CADASTRO);

        setUuid(pedido);
        BigDecimal valorProdutos;
        valorProdutos = tabelaItens.getValorTotalProdutos(pedido.getUuid());

        BigDecimal valorPizzas;
        valorPizzas = tabelaItens.getValorTotalPizzas(pedido.getUuid());

        List<ItemPedido> itemProdutos = pedido.getItens()
                .stream()
                .filter(item -> item.getProduto() != null)
                .collect(Collectors.toList());

        List<ItemPedido> itemPizzas = pedido.getItens()
                .stream()
                .filter(item -> item.getPizza() != null)
                .collect(Collectors.toList());

        mv.addObject("itensProdutos", itemProdutos);
        mv.addObject("itensPizzas", itemPizzas);
        mv.addObject("valorTotalProdutos", valorProdutos);
        mv.addObject("valorTotalPizzas", valorPizzas);
        mv.addObject("valorTotal", valorProdutos.add(valorPizzas));
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Pedido pedido = pedidos.findOne(id);
        setUuid(pedido);

        pedido.getItens().forEach(item -> {
            if (item.getProduto() != null) {
                tabelaItens.adicionarItem(pedido.getUuid(), item.getProduto(), item.getQuantidade());
            }

            if (item.getPizza() != null) {
                tabelaItens.adicionarItemPizza(pedido.getUuid(), item.getPizza(), item.getQuantidade());
            }
        });

        ModelAndView mv = novo(pedido);
        mv.addObject(pedido);
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
        mv.addObject("itensProdutos", tabelaItens.getItensProdutos(uuid));
        mv.addObject("valorTotal", tabelaItens.getValorTotalProdutos(uuid));
        return mv;
    }

    private ModelAndView mvTabelaItensPizza(String uuid) {
        ModelAndView mv = new ModelAndView("pedido/TabelaItensPizza");
        mv.addObject("itensPizzas", tabelaItens.getItensPizzas(uuid));
        mv.addObject("valorTotal", tabelaItens.getValorTotalPizzas(uuid));
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Pedido pedido, RedirectAttributes attributes, Model model) {

        pedido.setItens(tabelaItens.getItens(pedido.getUuid()));
        pedido.setValorTotal(tabelaItens.getItens(pedido.getUuid())
                .stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO));

        try {
            pedido = pedidosService.salvar(pedido);
        } catch (RuntimeException e) {
            model.addAttribute("mensagemErro", e.getMessage());
            return novo(pedido);
        }
        attributes.addFlashAttribute("mensagem", "Pedido: " + pedido.getId() + " salvo com sucesso!");
        return new ModelAndView("redirect:/mesas");
    }

    public void setUuid(Pedido pedido) {
        if (StringUtils.isEmpty(pedido.getUuid())) {
            pedido.setUuid(UUID.randomUUID().toString());
        }
    }
}
