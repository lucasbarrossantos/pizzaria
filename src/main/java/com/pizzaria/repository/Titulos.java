package com.pizzaria.repository;

import com.pizzaria.model.Titulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface Titulos extends JpaRepository<Titulo, Long> {

    @Query("select sum (t.valorPago) from Titulo as t where month(t.dataDeEmissao) = ?1")
    Optional<BigDecimal> valorTotalNoMes(int monthValue);

    @Query("select sum (t.valorPago) from Titulo as t " +
            "where day(cast(t.dataDoPagamento as date)) = day(cast(now() as date))")
    Optional<BigDecimal> valoresDoDia();

}
