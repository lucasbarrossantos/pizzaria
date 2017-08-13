package com.pizzaria.service;

import com.pizzaria.model.Pedido;
import com.pizzaria.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PedidosService {

    @Autowired
    private Pedidos pedidos;

    public Pedido salvar(Pedido pedido){
        pedido.setDataPedido(LocalDate.now());
        return pedidos.saveAndFlush(pedido);
    }

}
