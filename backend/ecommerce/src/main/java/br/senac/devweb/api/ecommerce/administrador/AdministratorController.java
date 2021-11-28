package br.senac.devweb.api.ecommerce.administrador;

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
@RequestMapping("/administrador")
@AllArgsConstructor
@CrossOrigin("*")
public class AdministratorController {
    private AdministratorService administratorService;

    @PostMapping("/")
    public ResponseEntity<AdministradorRepresentation.AdministradorDetail> createAdministrador(
            @Valid @RequestBody AdministradorRepresentation.AdministradorRep administradorRep
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        AdministradorRepresentation.AdministradorDetail.from(
                                this.administratorService.saveAdministrador(administradorRep)
                        )
                );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorRepresentation.AdministradorDetail> updateAdministrador(
            @PathVariable("id") Long id,
            @Valid @RequestBody AdministradorRepresentation.AdministradorRep administradorRep
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AdministradorRepresentation.AdministradorDetail.from(
                                this.administratorService.updateAdministrador(id, administradorRep)
                        )
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorRepresentation.AdministradorDetail> readAdministradorById(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        AdministradorRepresentation.AdministradorDetail.from(
                                this.administratorService.getAdministrador(id)
                        )
                );
    }

    @GetMapping("/")
    public ResponseEntity<Pagination> readAllAdministradores(
            @QuerydslPredicate(root = Administrador.class) Predicate filters,
            @Valid @RequestParam(name = "selectedPage", defaultValue = "1") Integer selectedPage,
            @RequestParam(name = "pageSize", required = false, defaultValue = "20") Integer pageSize
    ) {
        if (selectedPage <= 0)  {
            throw new IllegalArgumentException("O número da página não pode ser 0 ou menor que 0");
        }

        Pageable pageRequest = PageRequest.of(selectedPage-1, pageSize);

        Page<Administrador> administradorPage = this.administratorService.getAllAdministradores(filters, pageRequest);

        Pagination pagination = Pagination
                .builder()
                .content(
                        AdministradorRepresentation.AdministradorList.from(administradorPage.getContent())
                )
                .selectedPage(selectedPage)
                .pageSize(pageSize)
                .pageCount(administradorPage.getTotalPages())
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pagination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AdministradorRepresentation.AdministradorDetail> deleteAdministrador(
            @PathVariable("id") Long id
    ) {
        this.administratorService.deleteAdministrador(id);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
