package com.pizzaria.model;

import com.pizzaria.model.enumeration.StatusPedido;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Entity
@Table(name = "pedido")
@DynamicUpdate
public class Pedido {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    private String observacao;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario")
    private Usuario vendedor;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ANDAMENTO;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ItemPedido> itens = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private Mesa mesa;

    @Transient
    private String uuid;

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

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        this.itens.forEach(item -> item.setPedido(this));
        this.mesa.setPedido(this);
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", observacao='" + observacao + '\'' +
                ", dataPedido=" + dataPedido +
                ", valorTotal=" + valorTotal +
                ", vendedor=" + vendedor +
                ", status=" + status +
                '}';
    }
}
