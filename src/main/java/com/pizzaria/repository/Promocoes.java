package com.pizzaria.repository;

import com.pizzaria.model.Promocao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Promocoes extends JpaRepository<Promocao, Long> {

}
