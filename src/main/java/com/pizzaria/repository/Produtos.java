package com.pizzaria.repository;

import com.pizzaria.model.Produto;
import com.pizzaria.repository.helper.produto.ProdutosQueries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Produtos extends JpaRepository<Produto, Long>, ProdutosQueries {

}
