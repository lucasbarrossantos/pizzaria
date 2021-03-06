package com.pizzaria.model;

import com.pizzaria.model.enumeration.Tamanho;
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
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "pizza")
@DynamicUpdate
public class Pizza extends Versao {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Selecione o tamanho")
    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @NotBlank(message = "Descrição não pode estar em branco")
    @Column(length = 60)
    private String descricao;

    private String borda;

    private String adicional;

    @Column(length = 90)
    private String saborPizza;

    @NumberFormat(pattern = "#,##0.00")
    @NotNull(message = "Valor unitário deve ser informado")
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @Size(min = 1, message = "Selecione pelo menos um sabor")
    @ManyToMany
    @JoinTable(name = "pizza_sabor", joinColumns = @JoinColumn(name = "codigo_pizza")
            , inverseJoinColumns = @JoinColumn(name = "codigo_sabor"))
    private List<Sabor> sabores = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Promocao promocao;

    private String foto;

    @Column(name = "content_type")
    private String contentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public String getBorda() {
        return borda;
    }

    public void setBorda(String borda) {
        this.borda = borda;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public List<Sabor> getSabores() {
        return sabores;
    }

    public void setSabores(List<Sabor> sabores) {
        this.sabores = sabores;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getSaborPizza() {
        return saborPizza;
    }

    public void setSaborPizza(String saborPizza) {
        this.saborPizza = saborPizza;
    }

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        this.sabores.forEach(sabor -> this.setSaborPizza(sabor.getDescricao()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pizza)) return false;

        Pizza pizza = (Pizza) o;

        return getId().equals(pizza.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", tamanho=" + tamanho +
                ", descricao='" + descricao + '\'' +
                ", borda='" + borda + '\'' +
                ", adicional='" + adicional + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", foto='" + foto + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
