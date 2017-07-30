package com.pizzaria.repository;

import com.pizzaria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pizzas extends JpaRepository<Pizza, Long> {

}
