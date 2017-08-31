package com.pizzaria.repository;

import com.pizzaria.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Grupos extends JpaRepository<Grupo, Long> {

    @Query("select g from Grupo as g inner join g.permissoes where g.id = ?1")
    Grupo grupoComPermissoes(Long id);
}
