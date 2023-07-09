package com.dolts.controledehotel.utils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class ListQueryBuilder<T> {

    private List<CriteriosDeBusca> criteriaList;

    public ListQueryBuilder() {
        this.criteriaList = new ArrayList<>();
    }

    public void with(String key, String operation, Object value) {
        criteriaList.add(new CriteriosDeBusca(key, operation, value));
    }

    public Specification<T> build() {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                for (CriteriosDeBusca criteria : criteriaList) {
                    if (criteria.getOperation().equals("equals")) {
                        predicates.add(criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    } else if (criteria.getOperation().equals("like")) {
                        predicates.add(criteriaBuilder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                    } else if (criteria.getOperation().equals("greaterThan")) {
                        predicates.add(criteriaBuilder.greaterThan(root.get(criteria.getKey()), (Comparable) criteria.getValue()));
                    } else if (criteria.getOperation().equals("lessThan")) {
                        predicates.add(criteriaBuilder.lessThan(root.get(criteria.getKey()), (Comparable) criteria.getValue()));
                    } else if (criteria.getOperation().equals("greaterThanOrEqual")) {
                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue()));
                    } else if (criteria.getOperation().equals("lessThanOrEqual")) {
                        predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), (Comparable) criteria.getValue()));
                    } else if (criteria.getOperation().equals("between")) {
                        Comparable[] values = (Comparable[]) criteria.getValue();
                        predicates.add(criteriaBuilder.between(root.get(criteria.getKey()), values[0], values[1]));
                    } else if (criteria.getOperation().equals("isNull")) {
                        predicates.add(criteriaBuilder.isNull(root.get(criteria.getKey())));
                    } else if (criteria.getOperation().equals("isNotNull")) {
                        predicates.add(criteriaBuilder.isNotNull(root.get(criteria.getKey())));
                    }
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}

