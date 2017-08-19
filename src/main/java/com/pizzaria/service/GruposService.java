package com.pizzaria.service;

import com.pizzaria.model.Grupo;
import com.pizzaria.repository.Grupos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GruposService {

    @Autowired
    private Grupos grupos;

    public Grupo salvar(Grupo grupo){
        return grupos.saveAndFlush(grupo);
    }

}
