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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String observacao;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "hora_pedido")
    private LocalTime horaPedido;

    @Column(name = "data_hora_entrega")
    private LocalDateTime dataHoraEntrega;

    @Column(name = "valor_frete")
    private BigDecimal valorFrete;

    @Column(name = "valor_desconto")
    private BigDecimal valorDesconto;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario")
    private Usuario vendedor;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.ANDAMENTO;

    @Size(min = 1, message = "Adicione pelo menos um item ao pedido")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

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

    public LocalTime getHoraPedido() {
        return horaPedido;
    }

    public void setHoraPedido(LocalTime horaPedido) {
        this.horaPedido = horaPedido;
    }

    public LocalDateTime getDataHoraEntrega() {
        return dataHoraEntrega;
    }

    public void setDataHoraEntrega(LocalDateTime dataHoraEntrega) {
        this.dataHoraEntrega = dataHoraEntrega;
    }

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }

    public BigDecimal getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(BigDecimal valorDesconto) {
        this.valorDesconto = valorDesconto;
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

    @PrePersist
    @PreUpdate
    private void prePersistUpdate() {
        this.itens.forEach(item -> item.setPedido(this));
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", observacao='" + observacao + '\'' +
                ", dataPedido=" + dataPedido +
                ", horaPedido=" + horaPedido +
                ", dataHoraEntrega=" + dataHoraEntrega +
                ", valorFrete=" + valorFrete +
                ", valorDesconto=" + valorDesconto +
                ", valorTotal=" + valorTotal +
                ", vendedor=" + vendedor +
                ", status=" + status +
                '}';
    }
}
