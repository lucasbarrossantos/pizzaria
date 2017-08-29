package com.pizzaria.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "fornecedor")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar em branco")
    @Column(length = 60)
    private String nomeFantasia;

    @NotBlank(message = "Razão social não pode estar em branco")
    @Column(length = 60)
    private String razaoSocial;

    @NotBlank(message = "Informe o CNPJ")
    @Column(length = 20)
    private String cnpj;

    @Column(length = 60)
    private String inscricaoEstadual;

    @Column(length = 60)
    private String inscricaoMunicipal;

    @Embedded
    private Endereco endereco;

    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.LAZY)
    private List<Titulo> titulos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Titulo> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulo> titulos) {
        this.titulos = titulos;
    }

    @PrePersist
    @PreUpdate
    private void prePersistPreUpdate() {
        this.cnpj = removerFormatacao(this.cnpj);
        this.endereco.setCep(removerFormatacaoCep(this.endereco.getCep()));
    }

    private String removerFormatacaoCep(String cep) {
        return cep.replaceAll("-", "");
    }

    private String removerFormatacao(String cnpj) {
        return cnpj.replaceAll("\\.|-|/", "");
    }

    @Override
    public String toString() {
        return "Fornecedor{" +
                "id=" + id +
                ", nomeFantasia='" + nomeFantasia + '\'' +
                ", razaoSocial='" + razaoSocial + '\'' +
                ", cnpj='" + cnpj + '\'' +
                ", inscricaoEstadual='" + inscricaoEstadual + '\'' +
                ", inscricaoMunicipal='" + inscricaoMunicipal + '\'' +
                ", endereco=" + endereco +
                '}';
    }
}
