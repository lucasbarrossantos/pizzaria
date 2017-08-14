package com.pizzaria.model;

import com.pizzaria.model.enumeration.StatusMesa;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String observacao;

    @Enumerated(EnumType.STRING)
    private StatusMesa status = StatusMesa.LIVRE;

    @Transient
    private BigDecimal valorItens;

    @OneToMany(mappedBy = "mesa")
    private List<Pedido> pedidos = new ArrayList<>();

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

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Transient
    public String getCorCard() {
        if (this.status != null) {
            switch (this.status) {
                case LIVRE:
                    return "bg-green";
                case RESERVADA:
                    return "bg-cyan";
                case MANUTENCAO:
                    return "bg-red";
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
