package com.pizzaria.repository.helper.produto;

import com.pizzaria.model.Produto;
import com.pizzaria.repository.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutosQueries {

    Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable);

}
