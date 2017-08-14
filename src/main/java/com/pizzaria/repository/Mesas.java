package com.pizzaria.repository;

import com.pizzaria.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Mesas extends JpaRepository<Mesa, Long>{

}
