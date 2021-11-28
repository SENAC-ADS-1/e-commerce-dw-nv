package br.senac.devweb.api.ecommerce.venda;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VendaRepository extends PagingAndSortingRepository<Venda, Long>,
        QuerydslPredicateExecutor<Venda> {
}
