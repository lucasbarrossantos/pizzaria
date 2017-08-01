package com.pizzaria.repository;

import com.pizzaria.model.FormaDePagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormasDePagamento extends JpaRepository<FormaDePagamento, Long> {

}
