package com.pizzaria.service.dto;

import java.math.BigDecimal;

public class ProdutoDTO {

    private Long id;
    private String sku;
    private String descricao;
    private BigDecimal valorUnitario;

    public ProdutoDTO(Long id, String sku, String descricao, BigDecimal valorUnitario) {
        this.id = id;
        this.sku = sku;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
