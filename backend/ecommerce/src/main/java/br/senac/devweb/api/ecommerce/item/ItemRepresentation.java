package br.senac.devweb.api.ecommerce.item;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface ItemRepresentation {
    @Data
    @Getter
    @Setter
    class ItemRep {
        @NotNull(message = "O campo venda não pode ser nulo!")
        private Long venda;

        @NotNull(message = "O campo produto não pode ser nulo!")
        private Long produto;

        @NotNull(message = "O campo valor não pode ser nulo")
        private double valor;

        @NotNull(message = "O campo quantidade não pode ser nulo")
        private double quantidade;

        @NotNull(message = "O campo obs não pode ser nulo!")
        @Size(max = 100, message = "O campo obs deve conter no máximo 100 caracteres!")
        private String obs;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ItemDetail {
        private Long id;
        private Long venda;
        private Long produto;
        private double valor;
        private double quantidade;
        private String obs;

        public static ItemDetail from(Item item) {
            return ItemDetail
                    .builder()
                    .id(item.getId())
                    .venda(item.getVenda().getId())
                    .produto(item.getProduto().getId())
                    .valor(item.getValor())
                    .quantidade(item.getQuantidade())
                    .obs(item.getObs())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ItemList {
        private Long id;
        private Long venda;
        private Long produto;
        private double valor;
        private double quantidade;
        private String obs;

        public static ItemList from(Item item) {
            return ItemList
                    .builder()
                    .id(item.getId())
                    .venda(item.getVenda().getId())
                    .produto(item.getProduto().getId())
                    .valor(item.getValor())
                    .quantidade(item.getQuantidade())
                    .obs(item.getObs())
                    .build();
        }

        public static List<ItemList> from(List<Item> itemList) {
            return itemList
                    .stream()
                    .map(ItemList::from)
                    .collect(Collectors.toList());
        }
    }
}
