package br.senac.devweb.api.ecommerce.contato;

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
public class ContatoService {
    private final ContatoRepository contatoRepository;

    public Contato saveContato(ContatoRepresentation.ContatoRep contatoRep, Cliente cliente) {
        return this.contatoRepository.save(
                Contato
                        .builder()
                        .cliente(cliente)
                        .tipo(contatoRep.getTipo())
                        .recuperacao(contatoRep.getRecuperacao())
                        .email(contatoRep.getEmail())
                        .telefone(contatoRep.getTelefone())
                        .obs(contatoRep.getObs())
                        .build()
        );
    }

    public Contato getContato(Long id) {
        BooleanExpression filter = QContato.contato.id.eq(id);

        return this.contatoRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Contact não encontrado!"));
    }

    public Page<Contato> getAllContatos(Predicate filter, Pageable pageable) {
        return this.contatoRepository.findAll(filter, pageable);
    }

    public Contato updateContato(Long id, ContatoRepresentation.ContatoRep contatoRep, Cliente cliente) {
        Contato oldContato = this.getContato(id);

        Contato updatedContato = oldContato
                .toBuilder()
                .cliente(cliente)
                .tipo(contatoRep.getTipo())
                .recuperacao(contatoRep.getRecuperacao())
                .email(contatoRep.getEmail())
                .telefone(contatoRep.getTelefone())
                .obs(contatoRep.getObs())
                .build();

        return this.contatoRepository.save(updatedContato);
    }

    public void deleteContato(Long id) {
        Contato contato = this.getContato(id);

        this.contatoRepository.delete(contato);
    }
}
