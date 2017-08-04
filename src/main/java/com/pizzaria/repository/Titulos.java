package com.pizzaria.repository;

import com.pizzaria.model.Titulo;
import com.pizzaria.repository.helper.titulo.TitulosQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Titulos extends JpaRepository<Titulo, Long>, TitulosQueries {

}
