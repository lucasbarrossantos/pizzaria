package com.pizzaria.model.enumeration;

public enum StatusMesa {

    RESERVADA("Reservada"),
    LIVRE("Livre"),
    MANUTENCAO("Em manutenção"),
    OCUPADA("Ocupada");

    private String descricao;

    StatusMesa(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
