package com.pizzaria.repository.helper.titulo;

import com.pizzaria.model.Titulo;
import com.pizzaria.repository.filter.TituloFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class TitulosImpl implements TitulosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Titulo> filtrar(Titulo filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Titulo> criteriaQuery = builder.createQuery(Titulo.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Titulo> tituloRoot = criteriaQuery.from(Titulo.class);

        // Inner Join Para evitar o problema do n + 1
        tituloRoot.fetch("fornecedor", JoinType.LEFT);

        adicionarFiltro(filtro, builder, predicates, tituloRoot);

        criteriaQuery.select(tituloRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Titulo> query = manager.createQuery(criteriaQuery);
        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    private Long total(Titulo filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Titulo> criteriaQuery = builder.createQuery(Titulo.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Titulo> tituloRoot = criteriaQuery.from(Titulo.class);

        // Inner Join Para evitar o problema do n + 1
        tituloRoot.fetch("fornecedor", JoinType.LEFT);

        adicionarFiltro(filtro, builder, predicates, tituloRoot);

        criteriaQuery.select(tituloRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Titulo> query = manager.createQuery(criteriaQuery);
        return (long) query.getResultList().size();
    }

    private void adicionarFiltro(Titulo filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Titulo> tituloRoot) {

        if (filtro != null) {
            if (!StringUtils.isEmpty(filtro.getDescricao())) {
                predicates.add(builder.like(tituloRoot.get("descricao"), "%" + filtro.getDescricao() + "%"));
            }

            if (filtro.getFornecedor() != null){
                predicates.add(builder.equal(tituloRoot.get("fornecedor"), filtro.getFornecedor()));
            }

            if (filtro.getCentroDeCusto() != null){
                predicates.add(builder.equal(tituloRoot.get("centroDeCusto"), filtro.getCentroDeCusto()));
            }

            if (filtro.getFormaDePagamento() != null){
                predicates.add(builder.equal(tituloRoot.get("formaDePagamento"), filtro.getFormaDePagamento()));
            }
        }

    }

}
