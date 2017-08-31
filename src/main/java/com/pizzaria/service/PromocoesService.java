package com.pizzaria.service;

import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Promocoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocoesService {

    @Autowired
    private Promocoes promocoes;

    @Autowired
    private Pizzas pizzas;

    public Promocao salvar(Promocao promocao) {
        removendoPizzasDaPromocao(promocao);
        promocao.getPizzas().forEach(pizza -> pizza.setPromocao(promocao));
        return promocoes.save(promocao);
    }

    public void excluir(Promocao promocao){

        promocao.getPizzas()
                .forEach(pizza -> {
                    pizza.setPromocao(null);
                    pizzas.saveAndFlush(pizza);
                });

        try {
            promocoes.delete(promocao);
            promocoes.flush();
        }catch (RuntimeException e){
            throw new RuntimeException("Impossível apagar a promoção. " + promocao.getId());
        }
    }

    private void removendoPizzasDaPromocao(Promocao promocao) {
        pizzas.pizzasEmPromocao()
                .stream()
                .filter(pizza -> pizza.getPromocao().getId().equals(promocao.getId()))
                .forEach(pizza -> {
                    pizza.setPromocao(null);
                    pizzas.save(pizza);
                });
    }

}