package br.senac.devweb.api.ecommerce.cliente;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface ClienteRepresentation {
    @Data
    @Getter
    @Setter
    class ClienteRep {
        @NotNull(message = "O campo nomeCompleto não pode ser nulo!")
        @Size(max = 100, min = 1, message = "O campo nomeCompleto deve conter entre 1 e 100 caracteres!")
        private String nomeCompleto;

        @NotNull(message = "O campo usuario não pode ser nulo!")
        @Size(min = 1, max = 20, message = "O campo usuario deve conter entre 1 e 20 caracteres!")
        private String usuario;

        @NotNull(message = "O campo senha não pode ser nulo!")
        @Size(min = 1, max = 100,  message = "O campo senha deve conter entre 1 e 100 caracteres!")
        private String senha;

        @NotNull(message = "O campo dataNascimento não pode ser nulo!")
        @Past(message = "O campo dataNascimento não pode ser maior que hoje!")
        private LocalDate dataNascimento;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ClienteDetail {
        private Long id;
        private String nomeCompleto;
        private String usuario;
        private LocalDate dataNascimento;

        public static ClienteDetail from(Cliente cliente) {
            return ClienteDetail
                    .builder()
                    .id(cliente.getId())
                    .nomeCompleto(cliente.getNomeCompleto())
                    .usuario(cliente.getUsuario())
                    .dataNascimento(cliente.getDataNascimento())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ClienteList {
        private Long id;
        private String nomeCompleto;
        private String usuario;
        private LocalDate dataNascimento;

        public static ClienteList from(Cliente cliente) {
            return ClienteList
                    .builder()
                    .id(cliente.getId())
                    .nomeCompleto(cliente.getNomeCompleto())
                    .usuario(cliente.getUsuario())
                    .dataNascimento(cliente.getDataNascimento())
                    .build();
        }

        public static List<ClienteList> from(List<Cliente> clienteList) {
            return clienteList
                    .stream()
                    .map(ClienteList::from)
                    .collect(Collectors.toList());
        }
    }
}
