package com.pizzaria.model.enumeration;

public enum Situacao {

    COMPENSADO("Compensado"),
    PAGAMENTO_NAO_REALIZADO("Pagamento não realizado"),
    CANCELADO("Cancelado");

    private String descricao;

    Situacao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
