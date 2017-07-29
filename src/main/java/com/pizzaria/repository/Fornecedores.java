package com.pizzaria.repository;

import com.pizzaria.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Fornecedores extends JpaRepository<Fornecedor, Long> {

}
