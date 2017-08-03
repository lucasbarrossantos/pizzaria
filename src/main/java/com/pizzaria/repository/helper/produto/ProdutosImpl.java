package com.pizzaria.repository.helper.produto;

import com.pizzaria.model.Produto;
import com.pizzaria.repository.filter.ProdutoFilter;
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

public class ProdutosImpl implements ProdutosQueries {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Produto> filtrar(ProdutoFilter filtro, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = builder.createQuery(Produto.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Produto> produtoRoot = criteriaQuery.from(Produto.class);
        produtoRoot.fetch("categoria", JoinType.INNER); // Para evitar o problema do n + 1

        adicionarFiltro(filtro, builder, predicates, produtoRoot);

        criteriaQuery.select(produtoRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Produto> query = manager.createQuery(criteriaQuery);
        return new PageImpl<>(query.getResultList(), pageable, total(filtro));
    }

    /**
     *
     * @return count
     */
    private Long total(ProdutoFilter filtro) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Produto> criteriaQuery = builder.createQuery(Produto.class);
        List<Predicate> predicates = new ArrayList<>();
        Root<Produto> produtoRoot = criteriaQuery.from(Produto.class);

        adicionarFiltro(filtro, builder, predicates, produtoRoot);

        criteriaQuery.select(produtoRoot);
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Produto> query = manager.createQuery(criteriaQuery);
        return (long) query.getResultList().size();
    }

    private void adicionarFiltro(ProdutoFilter filtro, CriteriaBuilder builder, List<Predicate> predicates, Root<Produto> produtoRoot) {

        if (filtro != null) {
            if (!StringUtils.isEmpty(filtro.getSku())) {
                predicates.add(builder.equal(produtoRoot.get("sku"), filtro.getSku()));
            }

            if (!StringUtils.isEmpty(filtro.getDescricao())){
                predicates.add(builder.like(produtoRoot.get("descricao"), "%"+filtro.getDescricao()));
            }

            if (isCategoriaExistente(filtro)){
                predicates.add(builder.equal(produtoRoot.get("categoria"), filtro.getCategoria()));
            }

            if (filtro.getValorDe() != null){
                predicates.add(builder.greaterThanOrEqualTo(produtoRoot.get("valorUnitario"), filtro.getValorDe()));
            }

            if (filtro.getValorAte() != null){
                predicates.add(builder.lessThanOrEqualTo(produtoRoot.get("valorUnitario"), filtro.getValorAte()));
            }
        }

    }

    private boolean isCategoriaExistente(ProdutoFilter filtro) {
        return filtro.getCategoria() != null && filtro.getCategoria().getId() != null;
    }
}
