package com.pizzaria.repository;

import com.pizzaria.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

}