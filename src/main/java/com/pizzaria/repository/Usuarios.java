package com.pizzaria.repository;

import com.pizzaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmailIgnoreCase(String email);

    @Query("select distinct p.nome from Usuario as u inner join u.grupos g inner join g.permissoes p where u = ?1")
    List<String> permissoes(Usuario usuario);
}
