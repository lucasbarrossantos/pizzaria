package com.pizzaria.model;

import com.pizzaria.model.enumeration.StatusMesa;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
@DynamicUpdate
public class Mesa {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
    private Long id;

    private String observacao;

    private LocalDate horaCadastro;

    @NotBlank(message = "Informe o número da mesa")
    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusMesa status;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorItens;

    @OneToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusMesa getStatus() {
        return status;
    }

    public void setStatus(StatusMesa status) {
        this.status = status;
    }

    public BigDecimal getValorItens() {
        return valorItens;
    }

    public void setValorItens(BigDecimal valorItens) {
        this.valorItens = valorItens;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getHoraCadastro() {
        return horaCadastro;
    }

    public void setHoraCadastro(LocalDate horaCadastro) {
        this.horaCadastro = horaCadastro;
    }

    public boolean isPedidoExistente() {
        return this.pedido != null && this.pedido.getId() != null;
    }

    @Transient
    public String getCorCard() {
        if (this.status != null) {
            switch (this.status) {
                case LIVRE:
                    return "bg-orange";
                case RESERVADA:
                    return "bg-teal";
                case MANUTENCAO:
                    return "bg-pink";
                case OCUPADA:
                    return "bg-light-green";
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "Mesa{" +
                "id=" + id +
                ", observacao='" + observacao + '\'' +
                ", status=" + status +
                ", valorItens=" + valorItens +
                '}';
    }
}
