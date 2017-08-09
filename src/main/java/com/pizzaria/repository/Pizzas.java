package com.pizzaria.repository;

import com.pizzaria.model.Pizza;
import com.pizzaria.service.dto.PizzaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Pizzas extends JpaRepository<Pizza, Long> {

    @Query("select p from Pizza as p join p.promocao")
    List<Pizza> pizzasEmPromocao();

    @Query("select new com.pizzaria.service.dto.PizzaDTO(id, tamanho, saborPizza, valorUnitario) from Pizza as p where lower(p.saborPizza) like ?1% or lower(p.tamanho) like ?1%")
    List<PizzaDTO> porSaborOuTamanho(String saborOuTamanho);

}
