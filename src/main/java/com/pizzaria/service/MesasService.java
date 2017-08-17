package com.pizzaria.service;

import com.pizzaria.model.Mesa;
import com.pizzaria.model.Titulo;
import com.pizzaria.model.enumeration.*;
import com.pizzaria.repository.Mesas;
import com.pizzaria.repository.Pedidos;
import com.pizzaria.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class MesasService {

    @Autowired
    private Mesas mesas;

    @Autowired
    private Titulos titulos;

    @Autowired
    private Pedidos pedidos;

    public Mesa salvar(Mesa mesa) {
        return mesas.saveAndFlush(mesa);
    }

    public void fecharMesa(Mesa mesa) {
        Titulo titulo = new Titulo();
        mesa.setStatus(StatusMesa.LIVRE);

        // Titulo
        titulo.setDataDeEmissao(LocalDate.now());
        titulo.setDataDeValidade(LocalDate.now());
        titulo.setDataDoPagamento(LocalDate.now());
        titulo.setDescricao("Mesa - " + mesa.getNumero());
        titulo.setValor(mesa.getValorItens());
        titulo.setValorOriginal(mesa.getValorItens());
        titulo.setValorPago(mesa.getValorItens());
        titulo.setTipo(Tipo.ENTRADA);
        titulo.setSituacao(Situacao.COMPENSADO);
        titulo.setCentroDeCusto(CentroDeCusto.VENDAS);
        mesa.setValorItens(BigDecimal.ZERO);

        // Pedido
        mesa.getPedido().setStatus(StatusPedido.CONCLUIDO);
        pedidos.save(mesa.getPedido());
        mesa.setPedido(null);

        mesas.save(mesa);
        titulos.save(titulo);

    }
}
