package com.pizzaria.session;

import com.pizzaria.model.ItemPedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @SessionScope para ter uma TabelaItensPedido para cada sessão
 */

@SessionScope
@Component
public class TabelaItensPedido {

    private List<ItemPedido> itensProdutos = new ArrayList<>();
    private List<ItemPedido> itensPizzas = new ArrayList<>();
    private List<ItemPedido> itens = new ArrayList<>();

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal::add)    // Soma todos
                .orElse(BigDecimal.ZERO);  //  Se não tiver nada, retorna zero
    }

    public void adicionarItem(Produto produto, Integer quantidade) {
        Optional<ItemPedido> itemPedidoOptional = itensProdutos.stream()
                .filter(i -> i.getProduto().equals(produto))
                .findAny();

        ItemPedido itemPedido = null;
        if (itemPedidoOptional.isPresent()) {
            itemPedido = itemPedidoOptional.get();
            itemPedido.setQuantidade(itemPedido.getQuantidade() + quantidade);
        } else {
            itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setValorUnitario(produto.getValorUnitario());
            itensProdutos.add(0, itemPedido);
            itens.add(itemPedido); // Para ser sempre a primeira da lista
        }
    }

    public void adicionarItemPizza(Pizza pizza, Integer quantidade) {
        Optional<ItemPedido> itemPedidoOptional = itensPizzas.stream()
                .filter(i -> i.getPizza().equals(pizza))
                .findAny();

        ItemPedido itemPedido = null;
        if (itemPedidoOptional.isPresent()) {
            itemPedido = itemPedidoOptional.get();
            itemPedido.setQuantidade(itemPedido.getQuantidade() + quantidade);
        } else {
            itemPedido = new ItemPedido();
            itemPedido.setPizza(pizza);
            itemPedido.setQuantidade(quantidade);
            itemPedido.setValorUnitario(pizza.getValorUnitario());
            itemPedido.setProduto(null);
            itensPizzas.add(0, itemPedido);
            itens.add(itemPedido);
        }
    }

    public int total() {
        return itens.size();
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public List<ItemPedido> getItensProdutos() {
        return itensProdutos;
    }

    public List<ItemPedido> getItensPizzas() {
        return itensPizzas;
    }
}