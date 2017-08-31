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
        return grupos.save(grupo);
    }

    public void excluir(Grupo grupo) {
        try {
            grupos.delete(grupo);
            grupos.flush();
        }catch (RuntimeException e){
            throw new RuntimeException("Impossível apagar o grupo. " + grupo.getId());
        }
    }
}
