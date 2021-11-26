package br.senac.devweb.api.ecommerce.cliente;

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
public class ClienteService {
    private final ClienteRepository clientRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Cliente save(ClienteRepresentation.ClienteRep clientRep) {
        return this.clientRepository.save(
                Cliente
                        .builder()
                        .nomeCompleto(clientRep.getNomeCompleto())
                        .status(Cliente.Status.ATIVO)
                        .usuario(clientRep.getUsuario())
                        .senha(this.bCryptPasswordEncoder.encode(clientRep.getSenha()))
                        .dataNascimento(clientRep.getDataNascimento())
                        .build()
        );
    }

    public Cliente getClient(Long id) {
        BooleanExpression filter = QCliente.cliente.id.eq(id)
                .and(QCliente.cliente.status.eq(Cliente.Status.ATIVO));

        return this.clientRepository.findOne(filter).orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));
    }

    public Page<Cliente> getAllClients(Predicate filter, Pageable pageable) {
        return this.clientRepository.findAll(filter, pageable);
    }

    public Cliente update(Long id, ClienteRepresentation.ClienteRep clientRep) {
        Cliente oldData = this.getClient(id);

        Cliente updatedData = oldData
                .toBuilder()
                .nomeCompleto(clientRep.getNomeCompleto())
                .status(Cliente.Status.ATIVO)
                .usuario(clientRep.getUsuario())
                .senha(this.bCryptPasswordEncoder.encode(clientRep.getSenha()))
                .dataNascimento(clientRep.getDataNascimento())
                .build();

        return this.clientRepository.save(updatedData);
    }

    public void delete(Long id) {
        Cliente cliente = this.getClient(id);

        cliente.setStatus(Cliente.Status.INATIVO);

        this.clientRepository.save(cliente);
    }

}
