package com.pizzaria.repository;

import com.pizzaria.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Categorias extends JpaRepository<Categoria, Long> {

}
