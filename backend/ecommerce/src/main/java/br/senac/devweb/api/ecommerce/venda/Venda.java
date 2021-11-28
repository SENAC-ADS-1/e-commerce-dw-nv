package br.senac.devweb.api.ecommerce.venda;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.endereco.Endereco;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "VENDA")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo cliente não pode ser nulo!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Cliente.class)
    @JoinColumn(name = "IDCLIENTE")
    private Cliente cliente;

    @NotNull(message = "O campo endereco não pode ser nulo!")
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Endereco.class)
    @JoinColumn(name = "IDENDERECO")
    private Endereco endereco;

    @NotNull(message = "O campo criadoEm não pode ser nulo!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "CRIADOEM")
    private LocalDate criadoEm;

    @NotNull(message = "O campo obs não pode ser nulo!")
    @Size(min = 1, max = 100, message = "O campo obs deve conter entre 1 e 100 caracteres!")
    @Column(name = "OBS")
    private String obs;
}
