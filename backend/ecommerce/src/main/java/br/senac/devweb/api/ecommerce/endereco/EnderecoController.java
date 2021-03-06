package br.senac.devweb.api.ecommerce.endereco;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.cliente.ClienteService;
import br.senac.devweb.api.ecommerce.util.Pagination;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/endereco")
@AllArgsConstructor
@CrossOrigin("*")
public class EnderecoController {
    private EnderecoService enderecoService;
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<EnderecoRepresentation.EnderecoDetailCompleto> createEndereco(
            @Valid @RequestBody EnderecoRepresentation.EnderecoRep enderecoRep
    ) {
        Cliente cliente = this.clienteService.getCliente(enderecoRep.getCliente());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        EnderecoRepresentation.EnderecoDetailCompleto.from(
                                this.enderecoService.saveEndereco(enderecoRep, cliente))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnderecoRepresentation.EnderecoDetailCompleto> updateEndereco(
            @PathVariable("id") Long id,
            @Valid @RequestBody EnderecoRepresentation.EnderecoRep enderecoRep
    ) {
        Cliente cliente = this.clienteService.getCliente(enderecoRep.getCliente());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        EnderecoRepresentation.EnderecoDetailCompleto.from(
                                this.enderecoService.updateEnderecos(id, enderecoRep, cliente))
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoRepresentation.EnderecoDetailCompleto> readEnderecoById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        EnderecoRepresentation.EnderecoDetailCompleto.from(
                                this.enderecoService.getEndereco(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllEnderecos(
            @QuerydslPredicate(root = Endereco.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        BooleanExpression filter = Objects.isNull(filters) ?
                QEndereco.endereco.status.eq(Endereco.Status.ATIVO) :
                QEndereco.endereco.status.eq(Endereco.Status.ATIVO).and(filters);

        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O n??mero da p??gina n??o pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Endereco> addressPage = this.enderecoService.getAllEnderecos(filter, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        EnderecoRepresentation.EnderecoList.from(addressPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(addressPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EnderecoRepresentation.EnderecoDetailCompleto> deleteEndereco(
            @PathVariable("id") Long id
    ) {
        this.enderecoService.deleteEnderecos(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
