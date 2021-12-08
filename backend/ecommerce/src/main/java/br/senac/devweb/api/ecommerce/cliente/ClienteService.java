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

    public Cliente saveCliente(ClienteRepresentation.ClienteRep clientRep) {
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

    public Cliente getCliente(Long id) {
        BooleanExpression filter = QCliente.cliente.id.eq(id)
                .and(QCliente.cliente.status.eq(Cliente.Status.ATIVO));

        return this.clientRepository.findOne(filter).orElseThrow(() -> new NotFoundException("Cliente não encontrado!"));
    }

    public Page<Cliente> getAllClientes(Predicate filter, Pageable pageable) {
        return this.clientRepository.findAll(filter, pageable);
    }

    public Cliente updateClienteData(Long id, ClienteRepresentation.ClienteUpdateData clienteUpdateData) {
        Cliente oldData = this.getCliente(id);

        Cliente updatedData = oldData
                .toBuilder()
                .nomeCompleto(clienteUpdateData.getNomeCompleto())
                .status(Cliente.Status.ATIVO)
                .usuario(clienteUpdateData.getUsuario())
                .dataNascimento(clienteUpdateData.getDataNascimento())
                .build();

        return this.clientRepository.save(updatedData);
    }

    public void updateClientePassword(Long id, ClienteRepresentation.ClienteUpdatePassword clienteUpdatePassword) {
        Cliente oldData = this.getCliente(id);

        Cliente updatedData = oldData
                .toBuilder()
                .senha(bCryptPasswordEncoder.encode(clienteUpdatePassword.getSenha()))
                .build();

        this.clientRepository.save(updatedData);
    }

    public void deleteCliente(Long id) {
        Cliente cliente = this.getCliente(id);

        cliente.setStatus(Cliente.Status.INATIVO);

        this.clientRepository.save(cliente);
    }

}
