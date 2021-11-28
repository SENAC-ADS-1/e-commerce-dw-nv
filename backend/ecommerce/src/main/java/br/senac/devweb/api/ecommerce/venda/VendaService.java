package br.senac.devweb.api.ecommerce.venda;

import br.senac.devweb.api.ecommerce.cliente.Cliente;
import br.senac.devweb.api.ecommerce.endereco.Endereco;
import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class VendaService {
    private final VendaRepository vendaRepository;

    public Venda saveVenda(VendaRepresentation.VendaRep vendaRep, Cliente cliente, Endereco endereco) {
        return this.vendaRepository.save(
                Venda
                        .builder()
                        .cliente(cliente)
                        .endereco(endereco)
                        .criadoEm(LocalDate.now())
                        .obs(vendaRep.getObs())
                        .build()
        );
    }

    public Venda getVenda(Long id) {
        BooleanExpression filter = QVenda.venda.id.eq(id);

        return this.vendaRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Venda não encontrada!"));
    }

    public Page<Venda> getAllVendas(Predicate filter, Pageable pageable) {
        return this.vendaRepository.findAll(filter, pageable);
    }

    public Venda updateVenda(Long id, VendaRepresentation.VendaRep vendaRep, Cliente cliente, Endereco endereco) {
        Venda oldVenda = this.getVenda(id);

        Venda updatedVenda = oldVenda
                .toBuilder()
                .cliente(cliente)
                .endereco(endereco)
                .obs(vendaRep.getObs())
                .build();

        return this.vendaRepository.save(updatedVenda);
    }

    public void deleteVenda(Long id) {
        Venda venda = this.getVenda(id);

        this.vendaRepository.delete(venda);
    }
}
