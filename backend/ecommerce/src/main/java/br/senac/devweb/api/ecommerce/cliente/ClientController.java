package br.senac.devweb.api.ecommerce.cliente;

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
@RequestMapping("/client")
@AllArgsConstructor
@CrossOrigin("*")
public class ClientController {
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ClienteRepresentation.ClienteDetail> createClient(
            @Valid @RequestBody ClienteRepresentation.ClienteRep clienteRep
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ClienteRepresentation.ClienteDetail.from(
                                this.clienteService.save(clienteRep))
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteRepresentation.ClienteDetail> updateClient(
            @PathVariable("id") Long id,
            @Valid @RequestBody ClienteRepresentation.ClienteRep clienteRep
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ClienteRepresentation.ClienteDetail.from(
                                this.clienteService.update(id, clienteRep))
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteRepresentation.ClienteDetail> readClientById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ClienteRepresentation.ClienteDetail.from(
                                this.clienteService.getClient(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllClients(
            @QuerydslPredicate(root = Cliente.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        BooleanExpression filter = Objects.isNull(filters) ?
                QCliente.cliente.status.eq(Cliente.Status.ATIVO) :
                QCliente.cliente.status.eq(Cliente.Status.ATIVO).and(filters);

        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Cliente> clientePage = this.clienteService.getAllClients(filter, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        ClienteRepresentation.ClienteList.from(clientePage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(clientePage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteRepresentation.ClienteDetail> deleteClient(
            @PathVariable("id") Long id
    ) {
        this.clienteService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}