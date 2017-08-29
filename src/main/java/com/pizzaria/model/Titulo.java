package com.pizzaria.model;

import com.pizzaria.model.enumeration.CentroDeCusto;
import com.pizzaria.model.enumeration.FormaDePagamento;
import com.pizzaria.model.enumeration.Situacao;
import com.pizzaria.model.enumeration.Tipo;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

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
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Descrição deve ser informada")
    private String descricao;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_original")
    private BigDecimal valorOriginal;

    @NumberFormat(pattern = "#,##0.00")
    @NotNull(message = "Valor deve ser informado")
    private BigDecimal valor;

    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_pago")
    private BigDecimal valorPago;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_emissao")
    private LocalDate dataDeEmissao;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_de_validade")
    private LocalDate dataDeValidade;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "data_do_pagamento")
    private LocalDate dataDoPagamento;

    @Enumerated(EnumType.STRING)
    private FormaDePagamento formaDePagamento;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CentroDeCusto centroDeCusto;

    //@NotNull(message = "Informa o fornecedor")
    @ManyToOne
    private Fornecedor fornecedor;

    @NotNull(message = "Tipo deve ser informado")
    @Column(length = 40)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    @NotNull(message = "Situação deve ser informada")
    @Column(length = 40)
    @Enumerated(EnumType.STRING)
    private Situacao situacao;

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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
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
                ", tipo=" + tipo +
                ", situacao=" + situacao +
                ", fornecedor=" + fornecedor +
                '}';
    }

}
