package com.pizzaria.repository.filter;

import com.pizzaria.model.Categoria;
import com.pizzaria.validation.SKU;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class ProdutoFilter {

    private String sku;
    private String descricao;
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorDe;
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorAte;
    private Categoria categoria;

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

    public BigDecimal getValorDe() {
        return valorDe;
    }

    public void setValorDe(BigDecimal valorDe) {
        this.valorDe = valorDe;
    }

    public BigDecimal getValorAte() {
        return valorAte;
    }

    public void setValorAte(BigDecimal valorAte) {
        this.valorAte = valorAte;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
