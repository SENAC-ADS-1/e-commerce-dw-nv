package br.senac.devweb.api.ecommerce.item;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ItemRepository extends PagingAndSortingRepository<Item, Long>,
        QuerydslPredicateExecutor<Item> {
}
