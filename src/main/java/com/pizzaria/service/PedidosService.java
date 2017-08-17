package com.pizzaria.service;

import com.pizzaria.model.Pedido;
import com.pizzaria.model.enumeration.StatusMesa;
import com.pizzaria.repository.Mesas;
import com.pizzaria.repository.Pedidos;
import com.pizzaria.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class PedidosService {

    @Autowired
    private Pedidos pedidos;

    @Autowired
    private Produtos produtos;

    public Pedido salvar(Pedido pedido) {

        if (pedido.getItens().size() <= 0) {
            throw new RuntimeException("Adicione pelo menos um item ao pedido");
        }

        if (pedido.getMesa() == null) {
            throw new RuntimeException("Pedido sem mesa. Selecione a mesa para o pedido");
        }

        pedido.setDataPedido(LocalDate.now());
        atualizarMesaDoPedido(pedido);
        atualizarEstoque(pedido);
        return pedidos.save(pedido);
    }

    private void atualizarEstoque(Pedido pedido) {
        pedido.getItens().stream()
                .filter(itemPedido -> itemPedido.getProduto() != null)
                .forEach(item -> {
                    item.getProduto().setQuantidadeEstoque(item.getProduto().getQuantidadeEstoque() - item.getQuantidade());
                    produtos.saveAndFlush(item.getProduto());
                });
    }

    private void atualizarMesaDoPedido(Pedido pedido) {
        pedido.getMesa().setValorItens(pedido.getValorTotal());
        pedido.getMesa().setHoraCadastro(LocalDate.now());
        pedido.getMesa().setStatus(StatusMesa.OCUPADA);
        pedido.getMesa().setObservacao(pedido.getObservacao());
    }


}
