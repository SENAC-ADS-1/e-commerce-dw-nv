package br.senac.devweb.api.products.produto;

import br.senac.devweb.api.products.categoria.Categoria;
import br.senac.devweb.api.products.exceptions.NotFoundException;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public Produto saveProduto(ProdutoRepresentation.ProdutoRep produtoRep, Categoria categoria) {
        return this.produtoRepository.save(
                Produto
                        .builder()
                        .nome(produtoRep.getNome())
                        .descricao(produtoRep.getDescricao())
                        .complemento(Strings.isEmpty(produtoRep.getComplemento()) ? "" : produtoRep.getComplemento())
                        .fabricante(produtoRep.getFabricante())
                        .fornecedor(Strings.isEmpty(produtoRep.getFornecedor()) ? "" : produtoRep.getFornecedor())
                        .qtde(produtoRep.getQtde())
                        .valor(produtoRep.getValor())
                        .unidadeMedida(produtoRep.getUnidadeMedida())
                        .categoria(categoria)
                        .status(Produto.Status.ATIVO)
                        .build()
        );
    }

    public Produto getProduto(Long id) {
        BooleanExpression filter = QProduto.produto.id.eq(id)
                .and(QProduto.produto.status.eq(Produto.Status.ATIVO));

        return this.produtoRepository.findOne(filter)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado!"));
    }

    public Page<Produto> getAllProdutos(Predicate filter, Pageable pageable) {
        return this.produtoRepository.findAll(filter, pageable);
    }

    public Produto updateProduto(Long id, ProdutoRepresentation.ProdutoRep produtoRep, Categoria categoria) {
        Produto oldProduct = this.getProduto(id);

        Produto updatedProduct = oldProduct
                .toBuilder()
                .nome(produtoRep.getNome())
                .descricao(produtoRep.getDescricao())
                .complemento(Strings.isEmpty(produtoRep.getComplemento()) ? "" : produtoRep.getComplemento())
                .fabricante(produtoRep.getFabricante())
                .fornecedor(Strings.isEmpty(produtoRep.getFornecedor()) ? "" : produtoRep.getFornecedor())
                .qtde(produtoRep.getQtde())
                .valor(produtoRep.getValor())
                .unidadeMedida(produtoRep.getUnidadeMedida())
                .categoria(categoria)
                .status(Produto.Status.ATIVO)
                .build();

        return this.produtoRepository.save(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Produto produto = this.getProduto(id);

        produto.setStatus(Produto.Status.INATIVO);

        this.produtoRepository.save(produto);
    }
}
