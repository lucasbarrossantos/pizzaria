package com.pizzaria.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @Column(length = 60)
    private String descricao;

    private String borda;

    private String adicional;

    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull
    @OneToMany(mappedBy = "pizza", fetch = FetchType.LAZY)
    private List<Sabor> sabores = new ArrayList<>();

    @ManyToOne
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
