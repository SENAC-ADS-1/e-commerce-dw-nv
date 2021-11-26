package br.senac.devweb.api.ecommerce.cliente;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long>,
        QuerydslPredicateExecutor<Cliente>,
        QuerydslBinderCustomizer<QCliente> {

    List<Cliente> findAll(Predicate filter);

    default void customize(QuerydslBindings bindings, QCliente cliente) {
        bindings.bind(cliente.nomeCompleto).first(StringExpression::containsIgnoreCase);
    }

}
