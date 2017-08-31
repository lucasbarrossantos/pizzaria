package com.pizzaria.model;

import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "item_pedido")
public class ItemPedido extends Versao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private Integer quantidade;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @ManyToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    @ManyToOne
    private Pizza pizza;

    @ManyToOne
    private Produto produto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @NumberFormat(pattern = "#,##0.00")
    public BigDecimal getValorTotal() {
        return valorUnitario.multiply(new BigDecimal(quantidade));
    }
}
