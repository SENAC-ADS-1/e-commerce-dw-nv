package br.senac.devweb.api.ecommerce.administrador;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministradorRepository extends PagingAndSortingRepository<Administrador, Long>,
        QuerydslPredicateExecutor<Administrador>,
        QuerydslBinderCustomizer<QAdministrador> {
    List<Administrador> findAll(Predicate filter);

    default void customize(QuerydslBindings bindings, QAdministrador administrador) {
        bindings.bind(administrador.usuario).first(StringExpression::containsIgnoreCase);
        bindings.bind(administrador.email).first(StringExpression::containsIgnoreCase);
    }
}
