package br.senac.devweb.api.products.categoria;

import br.senac.devweb.api.products.util.Pagination;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/categoria")
@AllArgsConstructor
@CrossOrigin("*")
public class CategoriaController {

    private CategoriaService categoriaService;

    @PostMapping("/")
    public ResponseEntity<CategoriaRepresentation.CategoriaDetail> createCategory(
            @Valid @RequestBody CategoriaRepresentation.CategoriaRep categoriaRep
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CategoriaRepresentation.CategoriaDetail.from(
                                this.categoriaService.saveCategory(categoriaRep)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.CategoriaDetail> updateCategory(
            @PathVariable("id") Long id,
            @Valid @RequestBody CategoriaRepresentation.CategoriaRep categoriaRep
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CategoriaRepresentation.CategoriaDetail.from(
                                this.categoriaService.updateCategory(id, categoriaRep)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.CategoriaDetail> readCategoryById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CategoriaRepresentation.CategoriaDetail.from(
                                this.categoriaService.getCategory(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllCategories(
            @QuerydslPredicate(root = Categoria.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        BooleanExpression filter = Objects.isNull(filters) ?
                QCategoria.categoria.status.eq(Categoria.Status.ATIVO) :
                QCategoria.categoria.status.eq(Categoria.Status.ATIVO).and(filters);

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Categoria> categoriaPage = this.categoriaService.getAllCategories(filter, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        CategoriaRepresentation.CategoriaList.from(categoriaPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(categoriaPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @GetMapping("/unpaginated")
    public ResponseEntity<List<CategoriaRepresentation.CategoriaList>> getWithoutPagination(
            @QuerydslPredicate(root = Categoria.class) Predicate filters
    ) {
        BooleanExpression filter = Objects.isNull(filters) ?
                QCategoria.categoria.status.eq(Categoria.Status.ATIVO) :
                QCategoria.categoria.status.eq(Categoria.Status.ATIVO).and(filters);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        CategoriaRepresentation.CategoriaList.from(
                                this.categoriaService.getCategoriesWithoutPagination(filter)
                        )
                );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaRepresentation.CategoriaDetail> deleteCategory(
            @PathVariable("id") Long id
    ) {
        this.categoriaService.deleteCategory(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
