package br.senac.devweb.api.ecommerce.produto;

import br.senac.devweb.api.ecommerce.util.Pagination;
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
import java.util.Objects;

@RestController
@RequestMapping("/produto")
@AllArgsConstructor
@CrossOrigin("*")
public class ProdutoController {
    private ProdutoService produtoService;

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoRepresentation.ProdutoDetail> readProdutoById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ProdutoRepresentation.ProdutoDetail.from(this.produtoService.getProduto(id))
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllProdutos(
            @QuerydslPredicate(root = Produto.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        BooleanExpression filter = Objects.isNull(filters) ?
                QProduto.produto.status.eq(Produto.Status.ATIVO) :
                QProduto.produto.status.eq(Produto.Status.ATIVO).and(filters);

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Produto> produtoPage = this.produtoService.getAllProdutos(filter, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        ProdutoRepresentation.ProdutoList.from(produtoPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(produtoPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }
}
