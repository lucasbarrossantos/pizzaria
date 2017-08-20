package com.pizzaria.repository.helper.titulo;

import com.pizzaria.model.Titulo;
import com.pizzaria.repository.filter.TituloFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TitulosQueries {

    Page<Titulo> filtrar(Titulo titulo, Pageable pageable);

}
