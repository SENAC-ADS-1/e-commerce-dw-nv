package br.senac.devweb.api.ecommerce.venda;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.cliente.ClienteService;
import br.senac.devweb.api.ecommerce.endereco.Endereco;
import br.senac.devweb.api.ecommerce.endereco.EnderecoService;
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
@RequestMapping("/venda")
@AllArgsConstructor
@CrossOrigin("*")
public class VendaController {
    private VendaService vendaService;
    private ClienteService clienteService;
    private EnderecoService enderecoService;

    @PostMapping("/")
    public ResponseEntity<VendaRepresentation.VendaDetail> createVenda(
            @Valid @RequestBody VendaRepresentation.VendaRep vendaRep
    ) {
        Cliente cliente = this.clienteService.getCliente(vendaRep.getCliente());
        Endereco endereco = this.enderecoService.getEndereco(vendaRep.getEndereco());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        VendaRepresentation.VendaDetail.from(
                                this.vendaService.saveVenda(vendaRep, cliente, endereco)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendaRepresentation.VendaDetail> updateVenda(
            @PathVariable("id") Long id,
            @Valid @RequestBody VendaRepresentation.VendaRep vendaRep
    ) {
        Cliente cliente = this.clienteService.getCliente(vendaRep.getCliente());
        Endereco endereco = this.enderecoService.getEndereco(vendaRep.getEndereco());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        VendaRepresentation.VendaDetail.from(
                                this.vendaService.updateVenda(id, vendaRep, cliente, endereco)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendaRepresentation.VendaDetail> readVendaById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        VendaRepresentation.VendaDetail.from(
                                this.vendaService.getVenda(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllVendas(
            @QuerydslPredicate(root = Venda.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Venda> vendaPage = this.vendaService.getAllVendas(filters, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        VendaRepresentation.VendaList.from(vendaPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(vendaPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<VendaRepresentation.VendaDetail> deleteVenda(
            @PathVariable("id") Long id
    ) {
        this.vendaService.deleteVenda(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
