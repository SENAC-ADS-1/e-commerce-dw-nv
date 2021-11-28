package br.senac.devweb.api.ecommerce.produto;

import br.senac.devweb.api.ecommerce.categoria.Categoria;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo nome não pode ser nulo")
    @Size(min = 1, max = 30, message = "O campo nome deve conter entre 1 e 30 caracteres")
    @Column(name = "NOME")
    private String nome;

    @NotNull(message = "O campo descrição não pode ser nulo")
    @Size(min = 1, max = 255, message = "O campo descrição deve conter entre 1 e 255 caracteres")
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "COMPLEMENTO")
    private String complemento;

    @NotNull(message = "O campo valor não pode ser nulo")
    @Column(name = "VALOR")
    private Double valor;

    @NotNull(message = "O campo unidade de medida não pode ser nulo")
    @Column(name = "UNIDADEMEDIDA")
    private UnidadeMedida unidadeMedida;

    @NotNull(message = "O campo quantidade não pode ser nulo")
    @Column(name = "QTDE")
    private Double qtde;

    @NotNull(message = "O campo fabricante não pode ser nulo!")
    @Size(min = 1, max = 255, message = "O campo fabricante deve conter entre 1 e 255 caracteres!")
    @Column(name = "FABRICANTE")
    private String fabricante;

    @Column(name = "FORNECEDOR")
    private String fornecedor;

    @NotNull(message = "O campo status não pode ser nulo!")
    @Column(name = "STATUS")
    private Status status;

    @NotNull(message = "A categoria é obrigatória!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Categoria.class)
    @JoinColumn(name = "IDCATEGORIA")
    private Categoria categoria;

    public enum UnidadeMedida {
        UN,
        KG,
        ML,
        TN,
        MT,
        MT2,
        MT3,
        LT
    }

    public enum Status {
        ATIVO,
        INATIVO
    }
}