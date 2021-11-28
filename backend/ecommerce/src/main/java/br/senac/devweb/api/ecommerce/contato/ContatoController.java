package br.senac.devweb.api.ecommerce.contato;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.cliente.ClienteService;
import br.senac.devweb.api.ecommerce.util.Pagination;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/contato")
@AllArgsConstructor
@CrossOrigin("*")
public class ContatoController {
    private ContatoService contatoService;
    private ClienteService clienteService;

    @PostMapping("/")
    public ResponseEntity<ContatoRepresentation.ContatoDetail> createContato(
            @Valid @RequestBody ContatoRepresentation.ContatoRep contatoRep
    ) {
        Cliente cliente = this.clienteService.getCliente(contatoRep.getCliente());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        ContatoRepresentation.ContatoDetail.from(
                                this.contatoService.saveContato(contatoRep, cliente)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContatoRepresentation.ContatoDetail> updateContato(
            @PathVariable("id") Long id,
            @Valid @RequestBody ContatoRepresentation.ContatoRep contatoRep
    ) {
        Cliente cliente = this.clienteService.getCliente(contatoRep.getCliente());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ContatoRepresentation.ContatoDetail.from(
                                this.contatoService.updateContato(id, contatoRep, cliente)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoRepresentation.ContatoDetail> readContatoById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        ContatoRepresentation.ContatoDetail.from(
                                this.contatoService.getContato(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllContatos(
            @QuerydslPredicate(root = Contato.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Contato> contatoPage = this.contatoService.getAllContatos(filters, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        ContatoRepresentation.ContatoList.from(contatoPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(contatoPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContatoRepresentation.ContatoDetail> deleteContato(
            @PathVariable("id") Long id
    ) {
        this.contatoService.deleteContato(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
