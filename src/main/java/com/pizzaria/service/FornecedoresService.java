package com.pizzaria.service;

import com.pizzaria.model.Fornecedor;
import com.pizzaria.repository.Fornecedores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FornecedoresService {

    @Autowired
    private Fornecedores fornecedores;

    public void salvar(Fornecedor fornecedor){
        fornecedores.save(fornecedor);
    }

}
