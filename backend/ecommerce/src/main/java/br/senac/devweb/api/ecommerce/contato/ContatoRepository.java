package br.senac.devweb.api.ecommerce.contato;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ContatoRepository extends PagingAndSortingRepository<Contato, Long>,
        QuerydslPredicateExecutor<Contato>,
        QuerydslBinderCustomizer<QContato> {

    List<Contato> findAll(Predicate filter);

    default void customize(QuerydslBindings bindings, QContato contato) {
        bindings.bind(contato.obs).first(StringExpression::containsIgnoreCase);
        bindings.bind(contato.email).first(StringExpression::containsIgnoreCase);
    }
}
