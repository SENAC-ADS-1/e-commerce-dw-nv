package br.senac.devweb.api.ecommerce.administrador;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "ADMINISTRADOR")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo usuario não pode ser nulo!")
    @Size(min = 1, max = 20, message = "O campo usuario deve conter entre 1 e 20 caracteres!")
    @Column(name = "USER")
    private String usuario;

    @NotNull(message = "O campo senha não pode ser nulo!")
    @Size(min = 1, max = 100,  message = "O campo senha deve conter entre 1 e 100 caracteres!")
    @Column(name = "SENHA")
    private String senha;

    @NotNull(message = "O campo permissao não pode ser nulo!")
    @Column(name = "PERMISSAO")
    private Permissao permissao;

    @NotNull(message = "O campo email não pode ser nulo!")
    @Size(min = 1, max = 100,  message = "O campo email deve conter entre 1 e 100 caracteres!")
    @Column(name = "EMAIL")
    private String email;

    public enum Permissao {
        ADMINISTRADOR,
        GERENTE
    }
}
