package br.senac.devweb.api.ecommerce.administrador;

import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdministratorService {
    private final AdministradorRepository administradorRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Administrador saveAdministrador(AdministradorRepresentation.AdministradorRep administradorRep) {
        return this.administradorRepository.save(
                Administrador
                        .builder()
                        .usuario(administradorRep.getUsuario())
                        .senha(this.bCryptPasswordEncoder.encode(administradorRep.getSenha()))
                        .permissao(administradorRep.getPermissao())
                        .email(administradorRep.getEmail())
                        .build()
        );
    }

    public Administrador getAdministrador(Long id) {
        BooleanExpression filter = QAdministrador.administrador.id.eq(id);

        return this.administradorRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Administrador não encontrado!"));
    }

    public Page<Administrador> getAllAdministradores(Predicate filter, Pageable pageable) {
        return this.administradorRepository.findAll(filter, pageable);
    }

    public Administrador updateAdministrador(Long id, AdministradorRepresentation.AdministradorRep administradorRep) {
        Administrador oldAdministrador = this.getAdministrador(id);

        Administrador updatedAdministrador = oldAdministrador
                .toBuilder()
                .usuario(administradorRep.getUsuario())
                .senha(this.bCryptPasswordEncoder.encode(administradorRep.getSenha()))
                .permissao(administradorRep.getPermissao())
                .email(administradorRep.getEmail())
                .build();

        return this.administradorRepository.save(updatedAdministrador);
    }

    public void deleteAdministrador(Long id) {
        Administrador administrador = this.getAdministrador(id);

        this.administradorRepository.delete(administrador);
    }
}
