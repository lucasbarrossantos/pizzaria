package com.pizzaria.service;

import com.pizzaria.model.Usuario;
import com.pizzaria.repository.Usuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UsuariosService {

    @Autowired
    private Usuarios usuarios;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void salvar(Usuario usuario){

        if (usuario.isNovo()) {
            usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
            usuario.setConfirmeSenha(usuario.getSenha());
        }

        if (usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
            throw new RuntimeException("Senha é obrigatória para novo usuário");
        }

        usuarios.save(usuario);
    }

    public void excluir(Usuario usuario) {
        try {
            usuarios.delete(usuario);
            usuarios.flush();
        }catch (RuntimeException e){
            throw new RuntimeException("Impossível apagar o usuário. " + usuario.getId());
        }
    }
}
