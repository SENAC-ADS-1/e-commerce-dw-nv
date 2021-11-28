package br.senac.devweb.api.ecommerce.endereco;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Long>,
        QuerydslPredicateExecutor<Endereco>,
        QuerydslBinderCustomizer<QEndereco> {

    List<Endereco> findAll(Predicate filter);

    default void customize(QuerydslBindings bindings, QEndereco endereco) {
        bindings.bind(endereco.complemento).first(StringExpression::containsIgnoreCase);
        bindings.bind(endereco.cep).first(StringExpression::containsIgnoreCase);
        bindings.bind(endereco.logradouro).first(StringExpression::containsIgnoreCase);
        bindings.bind(endereco.cidade).first(StringExpression::containsIgnoreCase);
        bindings.bind(endereco.estado).first(StringExpression::containsIgnoreCase);
    }
}
