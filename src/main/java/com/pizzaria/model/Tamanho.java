package com.pizzaria.model;

/**
 * Created by lucasbarros on 26/07/2017.
 */
public enum Tamanho {

    GRANDE("Grande 12 fatias"),
    PEQUENA("Pequena 9"),
    FAMILIA("Família 15"),
    MEDIA("Média 4");

    private String descricao;

    Tamanho(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
