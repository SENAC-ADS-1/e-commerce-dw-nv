package br.senac.devweb.api.ecommerce.categoria;

import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaService {
    private CategoriaRepository categoriaRepository;

    public Categoria getCategory(Long id) {
        BooleanExpression filter = QCategoria.categoria.id.eq(id)
                .and(QCategoria.categoria.status.eq(Categoria.Status.ATIVO));

        return this.categoriaRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada!"));
    }

    public Page<Categoria> getAllCategories(Predicate filter, Pageable pageable) {
        return this.categoriaRepository.findAll(filter, pageable);
    }

    public List<Categoria> getCategoriesWithoutPagination(Predicate filter) {
        return this.categoriaRepository.findAll(filter);
    }
}
