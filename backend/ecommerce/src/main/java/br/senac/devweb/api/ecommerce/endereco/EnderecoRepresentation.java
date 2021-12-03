package br.senac.devweb.api.ecommerce.endereco;

import br.senac.devweb.api.ecommerce.cliente.ClienteRepresentation;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface EnderecoRepresentation {
    @Data
    @Getter
    @Setter
    class EnderecoRep {
        @NotNull(message = "O campo cliente não pode ser nulo!")
        private Long cliente;

        @NotNull(message = "O campo type não pode ser nulo!")
        private Endereco.Tipo tipo;

        @NotNull(message = "O campo estado não pode ser nulo!")
        @Size(min = 1, max = 30, message = "O campo estado deve conter entre 1 e 30 caracteres!")
        private String estado;

        @NotNull(message = "O campo cidade não pode ser nulo!")
        @Size(min = 1, max = 30, message = "O campo cidade deve conter entre 1 e 30 caracteres!")
        private String cidade;

        @NotNull(message = "O campo cep não pode ser nulo!")
        @Size(min = 1, max = 8, message = "O campo cep deve conter entre 1 e 8 caracteres!")
        private String cep;

        @NotNull(message = "O campo logradouro não pode ser nulo!")
        @Size(min = 1, max = 100, message = "O campo logradouro deve conter entre 1 e 100 caracteres!")
        private String logradouro;

        @NotNull(message = "O campo numero não pode ser nulo!")
        private int numero;

        @NotNull(message = "O campo complemento não pode ser nulo!")
        @Size(min = 1, max = 100, message = "O campo complemento deve conter entre 1 e 100 caracteres!")
        private String complemento;
    }

    @Data
    @Getter
    @Setter
    @SuperBuilder
    class EnderecoDetail {
        private Long id;
        private Endereco.Tipo tipo;
        private String estado;
        private String cidade;
        private String cep;
        private String logradouro;
        private int numero;
        private String complemento;

        public static EnderecoDetail from(Endereco endereco) {
            return EnderecoDetail
                    .builder()
                    .id(endereco.getId())
                    .tipo(endereco.getTipo())
                    .estado(endereco.getEstado())
                    .cidade(endereco.getCidade())
                    .cep(endereco.getCep())
                    .logradouro(endereco.getLogradouro())
                    .numero(endereco.getNumero())
                    .complemento(endereco.getComplemento())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @SuperBuilder
    class EnderecoDetailCompleto extends EnderecoDetail {
        private ClienteRepresentation.ClienteDetail cliente;

        public static EnderecoDetailCompleto from(Endereco endereco) {
            return EnderecoDetailCompleto
                    .builder()
                    .id(endereco.getId())
                    .cliente(ClienteRepresentation.ClienteDetail.from(endereco.getCliente()))
                    .tipo(endereco.getTipo())
                    .estado(endereco.getEstado())
                    .cidade(endereco.getCidade())
                    .cep(endereco.getCep())
                    .logradouro(endereco.getLogradouro())
                    .numero(endereco.getNumero())
                    .complemento(endereco.getComplemento())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class EnderecoList {
        private Long id;
        private ClienteRepresentation.ClienteDetail cliente;
        private Endereco.Tipo tipo;
        private String state;
        private String city;
        private String cep;
        private String log;
        private int number;
        private String complement;

        public static EnderecoList from(Endereco endereco) {
            return EnderecoList.
                    builder()
                    .id(endereco.getId())
                    .cliente(ClienteRepresentation.ClienteDetail.from(endereco.getCliente()))
                    .tipo(endereco.getTipo())
                    .state(endereco.getEstado())
                    .city(endereco.getCidade())
                    .cep(endereco.getCep())
                    .log(endereco.getLogradouro())
                    .number(endereco.getNumero())
                    .complement(endereco.getComplemento())
                    .build();
        }

        public static List<EnderecoList> from(List<Endereco> enderecoList) {
            return enderecoList
                    .stream()
                    .map(EnderecoList::from)
                    .collect(Collectors.toList());
        }
    }
}
