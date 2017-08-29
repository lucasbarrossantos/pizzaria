package com.pizzaria.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    private Long id;

    private String descricao;

    @NotBlank
    @Column(length = 90, name = "nome_fantasia")
    private String nomeFantasia;

    @NotBlank
    @Column(length = 90, name = "razao_social")
    private String razaoSocial;

    @NotBlank
    @Column(length = 20)
    private String cnpj;

    @Column(length = 40, name = "inscricao_estadual")
    private String inscricaoEstadual;

    @Column(length = 60, name = "inscricao_municipal")
    private String inscricaoMunicipal;

    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY)
    private List<Usuario> usuarios = new ArrayList<>();
    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY)
    private List<Promocao> promocoes = new ArrayList<>();
    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();
    @OneToMany(mappedBy = "estabelecimento", fetch = FetchType.LAZY)
    private List<Titulo> titulos = new ArrayList<>();

    @Embedded
    private Endereco endereco;

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

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Promocao> getPromocoes() {
        return promocoes;
    }

    public void setPromocoes(List<Promocao> promocoes) {
        this.promocoes = promocoes;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public List<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulo> titulos) {
        this.titulos = titulos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Estabelecimento{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                ", inscricaoMunicipal='" + inscricaoMunicipal + '\'' +
                '}';
    }
}
