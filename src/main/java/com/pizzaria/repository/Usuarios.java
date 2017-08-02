package com.pizzaria.repository;

import com.pizzaria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Usuarios extends JpaRepository<Usuario, Long> {

}