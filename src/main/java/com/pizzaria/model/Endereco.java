package com.pizzaria.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Embeddable
public class Endereco {

    @Column(length = 60)
    private String endereco;

    @Column(length = 60)
    private String bairro;

    @Column(length = 60)
    private String cidade;

    @Column(length = 60)
    private String estado;

    @Column(length = 10)
    private String cep;

    @Column(length = 40)
    private String complemento;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}
