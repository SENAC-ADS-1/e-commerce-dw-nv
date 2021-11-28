package br.senac.devweb.api.ecommerce.item;

import br.senac.devweb.api.ecommerce.produto.Produto;
import br.senac.devweb.api.ecommerce.venda.Venda;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "ITEM")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo venda não pode ser nulo!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Venda.class)
    @JoinColumn(name = "IDVENDA")
    private Venda venda;

    @NotNull(message = "O campo produto não pode ser nulo!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Produto.class)
    @JoinColumn(name = "IDPRODUTO")
    private Produto produto;

    @NotNull(message = "O campo valor não pode ser nulo")
    @Column(name = "VALOR")
    private double valor;

    @NotNull(message = "O campo quantidade não pode ser nulo")
    @Column(name = "QUANTIDADE")
    private double quantidade;

    @NotNull(message = "O campo obs não pode ser nulo!")
    @Size(max = 100, message = "O campo obs deve conter no máximo 100 caracteres!")
    @Column(name = "OBS")
    private String obs;
}
