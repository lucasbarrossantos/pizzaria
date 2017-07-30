package com.pizzaria.repository;

import com.pizzaria.model.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Sabores extends JpaRepository<Sabor, Long> {

}
