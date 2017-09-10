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

    @Query("SELECT new com.pizzaria.service.dto.PedidoDTO(to_char(p.data_pedido::date, 'MM/YY')  as mes,   sum(p.valor_total) as total) " +
            " FROM Pedido AS p " +
            " where p.status = 'CONCLUIDO' \n" +
            " and p.dataPedido BETWEEN ?1 AND ?2 \n " +
            " group by to_char(p.data_pedido::date, 'MM/YY') \n" +
            " order by to_char(p.data_pedido::date, 'MM/YY') ASC ")
    List<PedidoDTO> totalPorMes(LocalDate seisMesesAtras, LocalDate hoje);
}
