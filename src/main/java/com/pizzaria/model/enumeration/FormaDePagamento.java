package com.pizzaria.model.enumeration;

public enum FormaDePagamento {

    DINHEIRO("Dinheiro"),
    CARTAO("Cartão");

    private String descricao;

    FormaDePagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
