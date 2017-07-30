package com.pizzaria.service;

import com.pizzaria.model.Pizza;
import com.pizzaria.repository.Pizzas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzasService {

    @Autowired
    private Pizzas pizzas;

    public Pizza salvar(Pizza pizza) {
        Pizza copia = pizzas.saveAndFlush(pizza);
        return copia;
    }
}
