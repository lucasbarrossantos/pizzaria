package com.pizzaria.model;

import com.pizzaria.model.enumeration.StatusMesa;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "mesa")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String observacao;

    private LocalDate horaCadastro;

    @NotBlank(message = "Informe o número da mesa")
    private String numero;

    @Enumerated(EnumType.STRING)
    private StatusMesa status = StatusMesa.LIVRE;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal valorItens = BigDecimal.ZERO;

    @OneToMany(mappedBy = "mesa", fetch = FetchType.LAZY)
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
                case OCUPADA:
                    return "bg-blue-grey";
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
