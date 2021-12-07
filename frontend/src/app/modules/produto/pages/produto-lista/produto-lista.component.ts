import { Component, OnInit } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { RouteService } from 'src/app/commons/services/route.service';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-produto-lista',
  templateUrl: './produto-lista.component.html',
  styleUrls: ['./produto-lista.component.scss']
})
export class ProdutoListaComponent implements OnInit {
  produtos = {} as IPageConfig<IProduto>;

  constructor(
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService,
    private routeService: RouteService
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.produtoService
      .getAll()
      .then(res => {
        this.produtos = res;
      })
      .catch(err => {
        console.log('Erro: ', err);
      });
  }

  getOne(produto: any) {
    this.routeService.navigate(`produto/visualizar/${produto.id}`);
  }

  cadastrarNovoProduto() {
		this.routeService.navigate(`/produto/cadastrar`);
	}

  atualizar(produto: any) {
    this.routeService.navigate(`produto/atualizar/${produto.id}`)
  }
}
