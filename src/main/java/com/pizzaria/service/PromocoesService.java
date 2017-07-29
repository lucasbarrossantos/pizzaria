package com.pizzaria.service;

import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Promocoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromocoesService {

    @Autowired
    private Promocoes promocoes;

    public Promocao salvar(Promocao promocao){
        return promocoes.save(promocao);
    }

}
