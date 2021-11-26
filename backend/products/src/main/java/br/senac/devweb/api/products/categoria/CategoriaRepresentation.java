package br.senac.devweb.api.products.categoria;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface CategoriaRepresentation {

    @Data
    @Getter
    @Setter
    class CategoriaRep {
        @NotNull(message = "O campo descrição não pode ser nulo!")
        @Size(max = 30, min = 1, message = "A descrição deve conter de 1 a 30 caracteres!")
        private String descricao;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class CategoriaDetail {
        private Long id;
        private String descricao;

        public static CategoriaDetail from(Categoria categoria) {
            return CategoriaDetail
                    .builder()
                    .id(categoria.getId())
                    .descricao(categoria.getDescricao())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class CategoriaList {
        private Long id;
        private String descricao;

        private static CategoriaList from(Categoria categoria) {
            return CategoriaList
                    .builder()
                    .id(categoria.getId())
                    .descricao(categoria.getDescricao())
                    .build();
        }

        public static List<CategoriaList> from(List<Categoria> categoriaList) {
            return categoriaList
                    .stream()
                    .map(CategoriaList::from)
                    .collect(Collectors.toList());
        }
    }
}
