package com.pizzaria.repository;


import com.pizzaria.model.Pedido;
import com.pizzaria.service.dto.PedidoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface Pedidos extends JpaRepository<Pedido, Long> {

    @Query("SELECT new com.pizzaria.service.dto.PedidoDTO(date_format(p.dataPedido, '%m/%Y') as mes, sum (p.valorTotal)) " +
            " FROM Pedido AS p " +
            " where p.status = 'CONCLUIDO' \n" +
            " and p.dataPedido BETWEEN ?1 AND ?2 \n " +
            " group by date_format(p.dataPedido, '%m/%Y')\n" +
            " order by date_format(p.dataPedido, '%m/%Y') DESC ")
    List<PedidoDTO> totalPorMes(LocalDate seisMesesAtras, LocalDate hoje);
}
