package com.pizzaria.service;

import com.pizzaria.model.Pizza;
import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Promocoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PromocoesService {

    @Autowired
    private Promocoes promocoes;

    @Autowired
    private Pizzas pizzas;

    public Promocao salvar(Promocao promocao) {
        List<String> pizzasPromocao = pizzas.pizzasEmPromocao().stream()
                .filter(pizza -> promocao.getPizzas().contains(pizza))
                .map(Pizza::getSaborPizza)
                .collect(Collectors.toList());

        if (pizzasPromocao.size() > 0 && promocao.getId() == null) {
            throw new RuntimeException("Já existe promoção para: " + pizzasPromocao);
        }

        removendoPizzaDaPromocao(promocao);
        promocao.getPizzas().forEach(pizza -> pizza.setPromocao(promocao));
        return promocoes.save(promocao);
    }

    private void removendoPizzaDaPromocao(Promocao promocao) {
        pizzas.pizzasEmPromocao()
                .stream()
                .filter(pizza -> pizza.getPromocao().getId().equals(promocao.getId()))
                .forEach(pizza -> {
                    pizza.setPromocao(null);
                    pizzas.save(pizza);
                });
    }

}