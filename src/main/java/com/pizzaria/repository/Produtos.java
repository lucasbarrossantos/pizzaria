package com.pizzaria.repository;

import com.pizzaria.model.Produto;
import com.pizzaria.repository.helper.produto.ProdutosQueries;
import com.pizzaria.service.dto.ProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {

    @Query("select new com.pizzaria.service.dto.ProdutoDTO(id, sku, descricao, valorUnitario, unidade) " +
            "from Produto as p where lower(p.sku) like ?1% or lower(p.descricao) like ?1% ")
    List<ProdutoDTO> porSkuOuNome(String skuOuNome);
}
