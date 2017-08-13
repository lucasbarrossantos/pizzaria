package com.pizzaria.session;
import com.pizzaria.model.ItemPedido;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionScope
@Component
public class TabelasItensSession {

    private Set<TabelaItensPedido> tabelas = new HashSet<>();

    public void adicionarItem(String uuid, Produto produto, int quantidade) {
        TabelaItensPedido tabela = buscarTabelaProdutoPorUUID(uuid);

        tabela.adicionarItem(produto, quantidade);
        tabelas.add(tabela);
    }

    public void adicionarItemPizza(String uuid, Pizza pizza, int quantidade) {
        TabelaItensPedido tabela = buscarTabelaPizzaPorUUID(uuid);
        tabela.adicionarItemPizza(pizza, quantidade);
        tabelas.add(tabela);
    }

    public void alterarQuantidadeItensProduto(String uuid, Produto produto, Integer quantidade) {
        TabelaItensPedido tabela = buscarTabelaProdutoPorUUID(uuid);
        tabela.alterarQuantidadeItensProduto(produto, quantidade);
        tabelas.add(tabela);
    }

    public void alterarQuantidadeItensPizza(String uuid, Pizza pizza, Integer quantidade) {
        TabelaItensPedido tabela = buscarTabelaPizzaPorUUID(uuid);
        tabela.alterarQuantidadeItensPizza(pizza, quantidade);
        tabelas.add(tabela);
    }

    public void excluirItemProduto(String uuid, Produto produto) {
        TabelaItensPedido tabela = buscarTabelaProdutoPorUUID(uuid);
        tabela.excluirItemProduto(produto);
    }

    public void excluirItemPizza(String uuid, Pizza pizza) {
        TabelaItensPedido tabela = buscarTabelaPizzaPorUUID(uuid);
        tabela.excluirItemPizza(pizza);
    }

    public List<ItemPedido> getItensProdutos(String uuid) {
        return buscarTabelaProdutoPorUUID(uuid).getItensProdutos();
    }

    public List<ItemPedido> getItensPizzas(String uuid) {
        return buscarTabelaPizzaPorUUID(uuid).getItensPizzas();
    }

    private TabelaItensPedido buscarTabelaProdutoPorUUID(String uuid) {
        return tabelas.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findAny()
                .orElse(new TabelaItensPedido(uuid));
    }

    private TabelaItensPedido buscarTabelaPizzaPorUUID(String uuid) {
        return tabelas.stream()
                .filter(t -> t.getUuid().equals(uuid))
                .findAny()
                .orElse(new TabelaItensPedido(uuid));
    }

    public BigDecimal getValorTotalProdutos(String uuid) {
        return buscarTabelaProdutoPorUUID(uuid).getValorTotalProdutos();
    }

    public BigDecimal getValorTotalPizzas(String uuid) {
        return buscarTabelaPizzaPorUUID(uuid).getValorTotalPizzas();
    }

    public BigDecimal getValoresProdutosPizzas(String uuid){
        System.out.println("Valor total dos itens: " + getValorTotalProdutos(uuid).add(getValorTotalPizzas(uuid)).divide(new BigDecimal(2)));
        return getValorTotalProdutos(uuid).add(getValorTotalPizzas(uuid)).divide(new BigDecimal(2));
    }
}
