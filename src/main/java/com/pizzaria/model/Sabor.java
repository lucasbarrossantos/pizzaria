package com.pizzaria.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * Created by lucasbarros on 25/07/2017.
 */

@Entity
@Table(name = "sabor")
@DynamicUpdate
public class Sabor {

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
        return "Sabor{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
