package com.pizzaria.repository;

import com.pizzaria.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pizzas extends JpaRepository<Pizza, Long> {

    @Query("select p from Pizza as p join p.promocao")
    List<Pizza> pizzasEmPromocao();

}
