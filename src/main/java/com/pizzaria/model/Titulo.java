package com.pizzaria.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "titulo")
public class Titulo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String descricao;

    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    private BigDecimal valor;

    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @Column(name = "data_de_emissao")
    private LocalDate dataDeEmissao;

    @Column(name = "data_de_validade")
    private LocalDate dataDeValidade;

    @Column(name = "data_do_pagamento")
    private LocalDate dataDoPagamento;

    @NotNull
    @JoinColumn(name = "pagamento_id")
    @ManyToOne
    private FormaDePagamento formaDePagamento;

    @NotNull
    @JoinColumn(name = "centro_de_custo_id")
    @ManyToOne
    private CentroDeCusto centroDeCusto;

    @NotNull
    @ManyToOne
    private Fornecedor fornecedor;

    @NotNull
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

    public BigDecimal getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(BigDecimal valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDate getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(LocalDate dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public LocalDate getDataDoPagamento() {
        return dataDoPagamento;
    }

    public void setDataDoPagamento(LocalDate dataDoPagamento) {
        this.dataDoPagamento = dataDoPagamento;
    }

    public FormaDePagamento getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public CentroDeCusto getCentroDeCusto() {
        return centroDeCusto;
    }

    public void setCentroDeCusto(CentroDeCusto centroDeCusto) {
        this.centroDeCusto = centroDeCusto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    @Override
    public String toString() {
        return "Titulo{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valorOriginal=" + valorOriginal +
                ", valor=" + valor +
                ", valorPago=" + valorPago +
                ", dataDeEmissao=" + dataDeEmissao +
                ", dataDeValidade=" + dataDeValidade +
                ", dataDoPagamento=" + dataDoPagamento +
                ", formaDePagamento=" + formaDePagamento +
                ", centroDeCusto=" + centroDeCusto +
                ", fornecedor=" + fornecedor +
                '}';
    }

}
