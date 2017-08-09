package com.pizzaria.model.enumeration;

/**
 * Created by lucasbarros on 26/07/2017.
 */
public enum Tamanho {

    PEQUENA("Pequena"),
    MEDIA("Média"),
    FAMILIA("Família");

    private String descricao;

    Tamanho(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
