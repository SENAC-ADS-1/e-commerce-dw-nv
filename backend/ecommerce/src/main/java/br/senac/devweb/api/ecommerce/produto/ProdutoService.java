package br.senac.devweb.api.ecommerce.produto;

import br.senac.devweb.api.ecommerce.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto getProduto(Long id) {
        BooleanExpression filter = QProduto.produto.id.eq(id)
                .and(QProduto.produto.status.eq(Produto.Status.ATIVO));

        return this.produtoRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
    }

    public Page<Produto> getAllProdutos(Predicate filter, Pageable pageable) {
        return this.produtoRepository.findAll(filter, pageable);
    }
}
