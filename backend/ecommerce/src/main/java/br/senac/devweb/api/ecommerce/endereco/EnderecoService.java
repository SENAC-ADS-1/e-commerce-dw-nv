package br.senac.devweb.api.ecommerce.endereco;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;

    public Endereco saveEndereco(EnderecoRepresentation.EnderecoRep enderecoRep, Cliente cliente) {
        return this.enderecoRepository.save(
                Endereco
                        .builder()
                        .cliente(cliente)
                        .tipo(enderecoRep.getTipo())
                        .estado(enderecoRep.getEstado())
                        .cidade(enderecoRep.getCidade())
                        .cep(enderecoRep.getCep())
                        .logradouro(enderecoRep.getLogradouro())
                        .numero(enderecoRep.getNumero())
                        .complemento(enderecoRep.getComplemento())
                        .status(Endereco.Status.ATIVO)
                        .build()
        );
    }

    public Endereco getEndereco(Long id) {
        BooleanExpression filter = QEndereco.endereco.id.eq(id)
                .and(QEndereco.endereco.status.eq(Endereco.Status.ATIVO));

        return this.enderecoRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Address não encontrado!"));
    }

    public Page<Endereco> getAllEnderecos(Predicate filter, Pageable pageable) {
        return this.enderecoRepository.findAll(filter, pageable);
    }

    public Endereco updateEnderecos(Long id, EnderecoRepresentation.EnderecoRep enderecoRep, Cliente cliente) {
        Endereco oldEndereco = this.getEndereco(id);

        Endereco updatedEndereco = oldEndereco
                .toBuilder()
                .cliente(cliente)
                .tipo(enderecoRep.getTipo())
                .estado(enderecoRep.getEstado())
                .cidade(enderecoRep.getCidade())
                .logradouro(enderecoRep.getLogradouro())
                .numero(enderecoRep.getNumero())
                .complemento(enderecoRep.getComplemento())
                .status(Endereco.Status.ATIVO)
                .build();

        return this.enderecoRepository.save(updatedEndereco);
    }

    public void deleteEnderecos(Long id) {
        Endereco endereco = this.getEndereco(id);

        endereco.setStatus(Endereco.Status.INATIVO);

        this.enderecoRepository.save(endereco);
    }
}
