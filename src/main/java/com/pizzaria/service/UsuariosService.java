package com.pizzaria.service;

import com.pizzaria.model.Usuario;
import com.pizzaria.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {

    @Autowired
    private Usuarios usuarios;

    public void salvar(Usuario usuario){
        usuarios.save(usuario);
    }

}
