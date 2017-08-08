package com.pizzaria.session;

import com.pizzaria.model.ItemPedido;
import com.pizzaria.model.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @SessionScope para ter uma TabelaItensPedido para cada sessão
 */

@SessionScope
@Component
public class TabelaItensPedido {

    private List<ItemPedido> itens = new ArrayList<>();

    public BigDecimal getValorTotal() {
        return itens.stream()
                .map(ItemPedido::getValorTotal)
                .reduce(BigDecimal::add)    // Soma todos
                .orElse(BigDecimal.ZERO);  //  Se não tiver nada, retorna zero
    }

    public void adicionarItem(Produto produto, Integer quantidade){
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(quantidade);
        itemPedido.setValorUnitario(produto.getValorUnitario());

        itens.add(itemPedido);
    }

    public int total(){
        return itens.size();
    }

    public List<ItemPedido> getItens() {
        return itens;
    }
}
