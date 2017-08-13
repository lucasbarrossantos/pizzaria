package com.pizzaria.repository;


import com.pizzaria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Long> {

}
