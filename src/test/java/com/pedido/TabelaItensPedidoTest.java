package com.pedido;

import com.pizzaria.model.Produto;
import com.pizzaria.pedido.TabelaItensPedido;
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
        BigDecimal v1 = new BigDecimal("4.50");
        p1.setValorUnitario(v1);

        Produto p2 = new Produto();
        BigDecimal v2 = new BigDecimal("5.50");
        p2.setValorUnitario(v2);

        tabelaItensPedido.adicionarItem(p1, 1);
        tabelaItensPedido.adicionarItem(p2, 2);

        assertEquals(new BigDecimal("15.50"), tabelaItensPedido.getValorTotal());
    }

}
