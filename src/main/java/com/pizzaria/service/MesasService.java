package com.pizzaria.service;

import com.pizzaria.model.Mesa;
import com.pizzaria.repository.Mesas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MesasService {

    @Autowired
    private Mesas mesas;

    public Mesa salvar(Mesa mesa){
        return mesas.saveAndFlush(mesa);
    }

}
