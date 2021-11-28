package br.senac.devweb.api.ecommerce.item;

import br.senac.devweb.api.ecommerce.produto.Produto;
import br.senac.devweb.api.ecommerce.produto.ProdutoService;
import br.senac.devweb.api.ecommerce.util.Pagination;
import br.senac.devweb.api.ecommerce.venda.Venda;
import br.senac.devweb.api.ecommerce.venda.VendaService;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
@CrossOrigin("*")
public class ItemController {
    private ItemService itemService;
    private VendaService vendaService;
    private ProdutoService produtoService;

    @PostMapping("/")
    public ResponseEntity<ItemRepresentation.ItemDetail> createItem(
            @Valid @RequestBody ItemRepresentation.ItemRep itemRep
    ) {
        Venda venda = this.vendaService.getVenda(itemRep.getVenda());
        Produto produto = this.produtoService.getProduct(itemRep.getProduto());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ItemRepresentation.ItemDetail.from(
                                this.itemService.saveItem(itemRep, venda, produto)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemRepresentation.ItemDetail> updateItem(
            @PathVariable("id") Long id,
            @Valid @RequestBody ItemRepresentation.ItemRep itemRep
    ) {
        Venda venda = this.vendaService.getVenda(itemRep.getVenda());
        Produto produto = this.produtoService.getProduct(itemRep.getProduto());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ItemRepresentation.ItemDetail.from(
                                this.itemService.updateItem(id, itemRep, venda, produto)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllItens(
            @QuerydslPredicate(root = Item.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Item> itemPage = this.itemService.getAllItens(filters, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        ItemRepresentation.ItemList.from(itemPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(itemPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ItemRepresentation.ItemDetail> deleteItem(
            @PathVariable("id") Long id
    ) {
        this.itemService.deleteItem(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
