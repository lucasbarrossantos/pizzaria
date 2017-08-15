package com.pizzaria.service;

import com.pizzaria.model.Pedido;
import com.pizzaria.model.enumeration.StatusMesa;
import com.pizzaria.repository.Mesas;
import com.pizzaria.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PedidosService {

    @Autowired
    private Pedidos pedidos;

    @Autowired
    private Mesas mesas;

    public Pedido salvar(Pedido pedido){

        if(pedido.getItens().size() <= 0){
            throw new RuntimeException("Adicione pelo menos um item ao pedido");
        }

        if(pedido.getMesa() == null){
            throw new RuntimeException("Pedido sem mesa. Selecione a mesa para o pedido");
        }

        pedido.setDataPedido(LocalDate.now());
        atualizarMesaDoPedido(pedido);
        return pedidos.saveAndFlush(pedido);
    }

    private void atualizarMesaDoPedido(Pedido pedido) {
        pedido.getMesa().setValorItens(pedido.getValorTotal());
        pedido.getMesa().setHoraCadastro(LocalDate.now());
        pedido.getMesa().setStatus(StatusMesa.OCUPADA);
        pedido.getMesa().setObservacao(pedido.getObservacao());
        mesas.saveAndFlush(pedido.getMesa());
    }

}
