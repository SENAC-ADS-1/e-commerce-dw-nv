package br.senac.devweb.api.ecommerce.item;

import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import br.senac.devweb.api.ecommerce.produto.Produto;
import br.senac.devweb.api.ecommerce.venda.Venda;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Item saveItem(ItemRepresentation.ItemRep itemRep, Venda venda, Produto produto) {
        return this.itemRepository.save(
                Item
                        .builder()
                        .venda(venda)
                        .produto(produto)
                        .valor(itemRep.getValor())
                        .quantidade(itemRep.getQuantidade())
                        .obs(itemRep.getObs())
                        .build()
        );
    }

    public Item getItem(Long id) {
        BooleanExpression filter = QItem.item.id.eq(id);

        return this.itemRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Item não encontrado"));
    }

    public Page<Item> getAllItens(Predicate filter, Pageable pageable) {
        return this.itemRepository.findAll(filter, pageable);
    }

    public Item updateItem(Long id, ItemRepresentation.ItemRep itemRep, Venda venda, Produto produto) {
        Item oldItem = this.getItem(id);

        Item updatedItem = oldItem
                .toBuilder()
                .venda(venda)
                .produto(produto)
                .valor(itemRep.getValor())
                .quantidade(itemRep.getQuantidade())
                .obs(itemRep.getObs())
                .build();

        return this.itemRepository.save(updatedItem);
    }

    public void deleteItem(Long id) {
        Item item = this.getItem(id);

        this.itemRepository.delete(item);
    }
}
