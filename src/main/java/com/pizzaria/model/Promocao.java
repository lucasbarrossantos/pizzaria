package com.pizzaria.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 25/07/2017.
 */

@Entity
@Table(name = "promocao")
@DynamicUpdate
public class Promocao {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    private Long id;

    @NotBlank(message = "Descrição deve ser informada")
    @Column(length = 90)
    private String descricao;

    @NumberFormat(pattern = "#,##0.00")
    @NotNull(message = "Valor deve ser informado")
    private BigDecimal valor;

    @Size(min = 1, message = "Selecione pelo menos uma pizza")
    @OneToMany(mappedBy = "promocao", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Pizza> pizzas = new ArrayList<>();

    @ManyToOne
    private Estabelecimento estabelecimento;

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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    @Override
    public String toString() {
        return "Promocao{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                '}';
    }
}
