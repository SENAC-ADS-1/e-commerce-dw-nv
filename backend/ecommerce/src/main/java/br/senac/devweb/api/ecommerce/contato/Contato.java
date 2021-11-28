package br.senac.devweb.api.ecommerce.contato;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "CONTATO")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo cliente não pode ser nulo!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "IDCLIENTE")
    private Cliente cliente;

    @NotNull(message = "O campo type não pode ser nulo!")
    @Column(name = "TIPO")
    private Tipo tipo;

    @NotNull(message = "O campo recovery não pode ser nulo!")
    @Size(min = 1, max = 30, message = "O campo recovery deve conter entre 1 e 30 caracteres!")
    @Column(name = "RECUPERACAO")
    private String recuperacao;

    @NotNull(message = "O campo email não pode ser nulo!")
    @Size(min = 1, max = 30, message = "O campo email deve conter entre 1 e 30 caracteres!")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "O campo telefone não pode ser nulo!")
    @Size(min = 1, max = 15, message = "O campo telefone deve conter entre 1 e 15 caracteres!")
    @Column(name = "TELEFONE")
    private String telefone;

    @NotNull(message = "O campo obs não pode ser nulo!")
    @Size(min = 1, max = 100, message = "O campo obs deve conter entre 1 e 100 caracteres!")
    @Column(name = "OBS")
    private String obs;

    public enum Tipo {
        COMERCIAL,
        PARTICULAR;
    }
}
