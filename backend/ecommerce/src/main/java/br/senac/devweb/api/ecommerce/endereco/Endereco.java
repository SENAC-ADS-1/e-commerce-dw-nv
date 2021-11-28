package br.senac.devweb.api.ecommerce.endereco;

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
@Entity(name = "ENDERECO")
public class Endereco {
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

    @NotNull(message = "O campo estado não pode ser nulo!")
    @Size(min = 1, max = 30, message = "O campo estado deve conter entre 1 e 30 caracteres!")
    @Column(name = "ESTADO")
    private String estado;

    @NotNull(message = "O campo cidade não pode ser nulo!")
    @Size(min = 1, max = 30, message = "O campo cidade deve conter entre 1 e 30 caracteres!")
    @Column(name = "CIDADE")
    private String cidade;

    @NotNull(message = "O campo cep não pode ser nulo!")
    @Size(min = 1, max = 8, message = "O campo cep deve conter entre 1 e 8 caracteres!")
    @Column(name = "CEP")
    private String cep;

    @NotNull(message = "O campo logradouro não pode ser nulo!")
    @Size(min = 1, max = 100, message = "O campo logradoura deve conter entre 1 e 100 caracteres!")
    @Column(name = "LOGRADOURO")
    private String logradouro;

    @NotNull(message = "O campo numero não pode ser nulo!")
    @Column(name = "NUMERO")
    private int numero;

    @NotNull(message = "O campo complemento não pode ser nulo!")
    @Size(min = 1, max = 100, message = "O campo complemento deve conter entre 1 e 100 caracteres!")
    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotNull(message = "O campo status não pode ser nulo!")
    @Column(name = "STATUS")
    private Status status;

    public enum Status {
        ATIVO,
        INATIVO
    }

    public enum Tipo {
        COMERCIAL,
        PARTICULAR,
        TERCEIRO;
    }
}
