package com.pizzaria.repository;

import com.pizzaria.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Permissoes extends JpaRepository<Permissao, Long> {

}
