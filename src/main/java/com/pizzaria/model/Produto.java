package com.pizzaria.model;

import com.pizzaria.validation.SKU;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "produto")
@DynamicUpdate
public class Produto {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    private Long id;

    @NotBlank(message = "Descrição não pode estar em branco")
    @Column(length = 100)
    private String descricao;

    @NotNull(message = "Valor unitário deve ser informado")
    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_unitario")
    private BigDecimal valorUnitario;

    @NotNull(message = "Valor da compra deve ser informado")
    @NumberFormat(pattern = "#,##0.00")
    @Column(name = "valor_compra")
    private BigDecimal valorCompra;

    @Column(length = 60)
    private String unidade;

    @NotNull(message = "Selecione uma categoria")
    @ManyToOne
    private Categoria categoria;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "Campo obrigatório")
    @SKU
    private String sku;

    @Lob
    @Column
    private String caracteristicas;

    @NumberFormat(pattern = "#,##0")
    @NotNull(message = "A quantidade em estoque é obrigatória")
    @Max(value = 9999, message = "A quantidade em estoque deve ser menor que 9.999")
    @Column(name = "quantidade_estoque")
    private Integer quantidadeEstoque;

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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public void setEstabelecimento(Estabelecimento estabelecimento) {
        this.estabelecimento = estabelecimento;
    }

    public BigDecimal getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(BigDecimal valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;

        Produto produto = (Produto) o;

        return getId().equals(produto.getId());
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", valorUnitario=" + valorUnitario +
                ", valorCompra=" + valorCompra +
                ", unidade='" + unidade + '\'' +
                ", categoria=" + categoria +
                ", sku='" + sku + '\'' +
                ", caracteristicas='" + caracteristicas + '\'' +
                ", quantidadeEstoque=" + quantidadeEstoque +
                '}';
    }
}
