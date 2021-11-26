package br.senac.devweb.api.products.categoria;

import br.senac.devweb.api.products.exceptions.NotFoundException;
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

    public Categoria saveCategory(CategoriaRepresentation.CategoriaRep categoriaRep) {
        return this.categoriaRepository.save(
                Categoria
                        .builder()
                        .descricao(categoriaRep.getDescricao())
                        .status(Categoria.Status.ATIVO)
                        .build()
        );
    }

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

    public Categoria updateCategory(Long id, CategoriaRepresentation.CategoriaRep categoriaRep) {
        Categoria oldCategory = this.getCategory(id);

        Categoria updatedCategory = oldCategory
                .toBuilder()
                .descricao(categoriaRep.getDescricao())
                .status(Categoria.Status.ATIVO)
                .build();

        return this.categoriaRepository.save(updatedCategory);
    }

    public void deleteCategory(Long id) {
        Categoria categoria = this.getCategory(id);

        categoria.setStatus(Categoria.Status.INATIVO);

        this.categoriaRepository.save(categoria);
    }
}
