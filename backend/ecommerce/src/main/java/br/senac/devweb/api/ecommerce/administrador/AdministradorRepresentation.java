package br.senac.devweb.api.ecommerce.administrador;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.stream.Collectors;

public interface AdministradorRepresentation {
    @Data
    @Getter
    @Setter
    class AdministradorRep {
        @NotNull(message = "O campo usuario não pode ser nulo!")
        @Size(min = 1, max = 20, message = "O campo usuario deve conter entre 1 e 20 caracteres!")
        private String usuario;

        @NotNull(message = "O campo senha não pode ser nulo!")
        @Size(min = 1, max = 100,  message = "O campo senha deve conter entre 1 e 100 caracteres!")
        private String senha;

        @NotNull(message = "O campo permissao não pode ser nulo!")
        private Administrador.Permissao permissao;

        @NotNull(message = "O campo email não pode ser nulo!")
        @Size(min = 1, max = 100,  message = "O campo email deve conter entre 1 e 100 caracteres!")
        private String email;
    }

    @Data
    @Getter
    @Setter
    @Builder
    class AdministradorDetail {
        private Long id;
        private String usuario;
        private Administrador.Permissao permissao;
        private String email;

        public static AdministradorDetail from(Administrador administrador) {
            return AdministradorDetail
                    .builder()
                    .id(administrador.getId())
                    .usuario(administrador.getUsuario())
                    .permissao(administrador.getPermissao())
                    .email(administrador.getEmail())
                    .build();
        }
    }

    @Data
    @Getter
    @Setter
    @Builder
    class AdministradorList {
        private Long id;
        private String usuario;
        private Administrador.Permissao permissao;
        private String email;

        public static AdministradorList from(Administrador administrador) {
            return AdministradorList
                    .builder()
                    .id(administrador.getId())
                    .usuario(administrador.getUsuario())
                    .permissao(administrador.getPermissao())
                    .email(administrador.getEmail())
                    .build();
        }

        public static List<AdministradorList> from(List<Administrador> administradorList) {
            return administradorList
                    .stream()
                    .map(AdministradorList::from)
                    .collect(Collectors.toList());
        }
    }
}
