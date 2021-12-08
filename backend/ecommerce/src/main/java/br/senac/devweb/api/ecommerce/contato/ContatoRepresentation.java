package br.senac.devweb.api.ecommerce.contato;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface ContatoRepresentation {

    @Data
    @Getter
    @Setter
    class ContatoRep {
        @NotNull(message = "O campo cliente não pode ser nulo!")
        private Long cliente;

        @NotNull(message = "O campo type não pode ser nulo!")
        private Contato.Tipo tipo;

        @NotNull(message = "O campo recuperacao não pode ser nulo!")
        @Size(min = 1, max = 30, message = "O campo recuperacao deve conter entre 1 e 30 caracteres!")
        private String recuperacao;

        @NotNull(message = "O campo email não pode ser nulo!")
        @Size(min = 1, max = 30, message = "O campo email deve conter entre 1 e 30 caracteres!")
        private String email;

        @NotNull(message = "O campo telefone não pode ser nulo!")
        @Size(min = 1, max = 15, message = "O campo telefone deve conter entre 1 e 15 caracteres!")
        private String telefone;

        @NotNull(message = "O campo obs não pode ser nulo!")
        @Size(min = 1, max = 100, message = "O campo obs deve conter entre 1 e 100 caracteres!")
        private String obs;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ContatoDetail {
        private Long id;
        private Long cliente;
        private Contato.Tipo tipo;
        private String recuperacao;
        private String email;
        private String telefone;
        private String obs;

        public static ContatoDetail from(Contato contato) {
            return ContatoDetail
                    .builder()
                    .id(contato.getId())
                    .cliente(contato.getCliente().getId())
                    .tipo(contato.getTipo())
                    .recuperacao(contato.getRecuperacao())
                    .email(contato.getEmail())
                    .telefone(contato.getTelefone())
                    .obs(contato.getObs())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class ContatoList {
        private Long id;
        private Long cliente;
        private Contato.Tipo tipo;
        private String recuperacao;
        private String email;
        private String telefone;
        private String obs;

        public static ContatoList from(Contato contato) {
            return ContatoList
                    .builder()
                    .id(contato.getId())
                    .cliente(contato.getCliente().getId())
                    .tipo(contato.getTipo())
                    .recuperacao(contato.getRecuperacao())
                    .email(contato.getEmail())
                    .telefone(contato.getTelefone())
                    .obs(contato.getObs())
                    .build();
        }

        public static List<ContatoList> from(List<Contato> contatoList) {
            return contatoList
                    .stream()
                    .map(ContatoList::from)
                    .collect(Collectors.toList());
        }
    }
}
