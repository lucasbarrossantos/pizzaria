package com.pizzaria.service;

import com.pizzaria.model.Pizza;
import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Promocoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromocoesService {

    @Autowired
    private Promocoes promocoes;

    @Autowired
    private Pizzas pizzas;

    public Promocao salvar(Promocao promocao) {
        promocao.getPizzas().stream().forEach(pizza -> pizza.setPromocao(promocao));
        List<String> pizzasPromocao = pizzas.pizzasEmPromocao().stream()
                .filter(pizza -> promocao.getPizzas().contains(pizza))
                    .map(Pizza::getSaborPiza)
                .collect(Collectors.toList());

        if (pizzasPromocao.size() > 0) {
            throw new RuntimeException("Já existe promoção para: " + pizzasPromocao);
        }

        return promocoes.save(promocao);
    }

}