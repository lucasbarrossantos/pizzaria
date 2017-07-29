package com.pizzaria.model;

/**
 * Created by lucasbarros on 26/07/2017.
 */

public enum StatusPedido {

    ANDAMENTO("Endamento"),
    CONCLUIDO("Concluído"),
    CANCELADO("Cancelado"),
    ESPERA("Espera");

    private String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
