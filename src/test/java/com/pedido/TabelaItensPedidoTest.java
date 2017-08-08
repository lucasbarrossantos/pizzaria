package com.pedido;

import com.pizzaria.model.Pizza;
import com.pizzaria.model.Produto;
import com.pizzaria.session.TabelaItensPedido;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TabelaItensPedidoTest {

    private TabelaItensPedido tabelaItensPedido;

    @Before
    public void setUp() {
        this.tabelaItensPedido = new TabelaItensPedido();
    }

    @Test
    public void calcularValorTotalSemItens() throws Exception {
        assertEquals(BigDecimal.ZERO, tabelaItensPedido.getValorTotal());
    }

    @Test
    public void calcularValorTotalComUmItem() throws Exception {
        Produto produto = new Produto();
        BigDecimal valor = new BigDecimal("4.50");
        produto.setValorUnitario(valor);

        tabelaItensPedido.adicionarItem(produto, 1);
        assertEquals(valor, tabelaItensPedido.getValorTotal());
    }

    @Test
    public void calcularValorTotalComVariosItens() throws Exception {
        Produto p1 = new Produto();
        p1.setId(1L);
        BigDecimal v1 = new BigDecimal("4.50");
        p1.setValorUnitario(v1);

        Produto p2 = new Produto();
        p2.setId(2L);
        BigDecimal v2 = new BigDecimal("5.50");
        p2.setValorUnitario(v2);

        tabelaItensPedido.adicionarItem(p1, 1);
        tabelaItensPedido.adicionarItem(p2, 2);

        assertEquals(new BigDecimal("15.50"), tabelaItensPedido.getValorTotal());
    }

    // Pizzas

    @Test
    public void calcularValorTotalComUmItemPizza() throws Exception {
        Pizza pizza = new Pizza();
        BigDecimal valor = new BigDecimal("25.00");
        pizza.setValorUnitario(valor);

        tabelaItensPedido.adicionarItemPizza(pizza, 1);
        assertEquals(valor, tabelaItensPedido.getValorTotal());
    }

    @Test
    public void deveManterTamanhoDaListaParaMesmosItens() throws Exception {
        Produto p1 = new Produto();
        p1.setId(1L);
        p1.setValorUnitario(new BigDecimal("4.50"));

        tabelaItensPedido.adicionarItem(p1, 1);
        tabelaItensPedido.adicionarItem(p1, 2);
        tabelaItensPedido.adicionarItem(p1, 3);
        tabelaItensPedido.adicionarItem(p1, 5);

        assertEquals(1, tabelaItensPedido.total());
        assertEquals(new BigDecimal("49.50"), tabelaItensPedido.getValorTotal());
    }
}
