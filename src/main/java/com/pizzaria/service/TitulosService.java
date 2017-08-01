package com.pizzaria.service;

import com.pizzaria.model.Titulo;
import com.pizzaria.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitulosService {

    @Autowired
    private Titulos titulos;

    public Titulo salvar(Titulo titulo){
        return titulos.saveAndFlush(titulo);
    }

}
