package com.pizzaria.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "tipo_produto")
public class Categoria extends Versao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Descrição deve ser informada!")
    @Column(length = 100)
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
        return "Categoria{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
