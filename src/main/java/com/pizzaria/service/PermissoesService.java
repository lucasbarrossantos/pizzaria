package com.pizzaria.service;

import com.pizzaria.model.Permissao;
import com.pizzaria.repository.Permissoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissoesService {

    @Autowired
    private Permissoes permissoes;

    public Permissao salvar(Permissao permissao){
        return permissoes.saveAndFlush(permissao);
    }

}
