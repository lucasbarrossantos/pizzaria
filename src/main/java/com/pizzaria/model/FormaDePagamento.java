package com.pizzaria.model;

import javax.persistence.*;

/**
 * Created by lucasbarros on 25/07/2017.
 */

@Entity
@Table(name = "forma_de_pagamento")
public class FormaDePagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 60)
    private String descricao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "FormaDePagamento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
