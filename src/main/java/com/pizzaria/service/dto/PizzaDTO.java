package com.pizzaria.service.dto;

import com.pizzaria.model.enumeration.Tamanho;

import java.math.BigDecimal;

public class PizzaDTO {

    private Long id;
    private String tamanho;
    private String saborPizza;
    private BigDecimal valorUnitario;

    public PizzaDTO(Long id, Tamanho tamanho, String saborPizza, BigDecimal valorUnitario) {
        this.id = id;
        this.tamanho = tamanho.getDescricao();
        this.saborPizza = saborPizza;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getSaborPizza() {
        return saborPizza;
    }

    public void setSaborPizza(String saborPizza) {
        this.saborPizza = saborPizza;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
