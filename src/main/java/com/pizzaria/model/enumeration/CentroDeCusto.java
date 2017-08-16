package com.pizzaria.model.enumeration;

public enum CentroDeCusto {

    VENDAS("Vendas"),
    DESPESAS("Despesas");

    private String descricao;

    CentroDeCusto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
