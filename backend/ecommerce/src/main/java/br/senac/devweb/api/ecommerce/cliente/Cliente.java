package br.senac.devweb.api.ecommerce.cliente;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "CLIENTE")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo nomeCompleto não pode ser nulo!")
    @Column(name = "NOMECOMPLETO")
    @Size(max = 100, min = 1, message = "O campo nomeCompleto deve conter entre 1 e 100 caracteres!")
    private String nomeCompleto;

    @NotNull(message = "O campo status não pode ser nulo!")
    @Column(name = "STATUS")
    private Status status;

    @NotNull(message = "O campo usuario não pode ser nulo!")
    @Column(name = "USUARIO")
    @Size(min = 1, max = 20, message = "O campo usuario deve conter entre 1 e 20 caracteres!")
    private String usuario;

    @NotNull(message = "O campo senha não pode ser nulo!")
    @Column(name = "SENHA")
    @Size(min = 1, max = 100,  message = "O campo senha deve conter entre 1 e 100 caracteres!")
    private String senha;

    @NotNull(message = "O campo dataNascimento não pode ser nulo!")
    @Column(name = "DATANASCIMENTO")
    @Past(message = "O campo dataNascimento não pode ser maior que hoje!")
    private LocalDate dataNascimento;

    public enum Status {
        ATIVO,
        INATIVO
    }
}
