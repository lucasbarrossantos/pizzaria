package com.pizzaria.repository;

import com.pizzaria.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentrosDeCusto extends JpaRepository<CentroDeCusto, Long> {

}
