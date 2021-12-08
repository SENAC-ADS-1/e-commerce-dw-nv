package br.senac.devweb.api.ecommerce.venda;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface VendaRepresentation {
    @Data
    @Getter
    @Setter
    class VendaRep {
        @NotNull(message = "O campo cliente não pode ser nulo!")
        private Long cliente;

        @NotNull(message = "O campo endereco não pode ser nulo!")
        private Long endereco;

        @NotNull(message = "O campo obs não pode ser nulo!")
        @Size(max = 100, message = "O campo obs deve conter no máximo 100 caracteres!")
        private String obs;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class VendaDetail {
        private Long id;
        private Long cliente;
        private Long endereco;
        private LocalDate criadoEm;
        private String obs;

        public static VendaDetail from(Venda venda) {
            return VendaDetail
                    .builder()
                    .id(venda.getId())
                    .cliente(venda.getCliente().getId())
                    .endereco(venda.getEndereco().getId())
                    .criadoEm(venda.getCriadoEm())
                    .obs(venda.getObs())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class VendaList {
        private Long id;
        private Long cliente;
        private Long endereco;
        private LocalDate criadoEm;
        private String obs;

        public static VendaList from(Venda venda) {
            return VendaList
                    .builder()
                    .id(venda.getId())
                    .cliente(venda.getCliente().getId())
                    .endereco(venda.getEndereco().getId())
                    .criadoEm(venda.getCriadoEm())
                    .obs(venda.getObs())
                    .build();
        }

        public static List<VendaList> from(List<Venda> vendaList) {
            return vendaList
                    .stream()
                    .map(VendaList::from)
                    .collect(Collectors.toList());
        }
    }
}
