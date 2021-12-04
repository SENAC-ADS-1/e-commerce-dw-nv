import { Component, OnInit } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICategoria } from 'src/app/modules/categoria/model/categoria.model';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-produto-cadastrar',
  templateUrl: './produto-cadastrar.component.html',
  styleUrls: ['./produto-cadastrar.component.scss']
})
export class ProdutoCadastrarComponent implements OnInit {
  categorias = {} as IPageConfig<ICategoria>;
  error = {} as any;

  constructor(
    private produtoService: ProdutoService,
    private categoriaService: CategoriaService,
		private routeService: RouteService
  ) { }

  ngOnInit(): void {
    this
      .categoriaService
      .getFullCategoria()
      .then(res => {
        this.categorias = res;
      });
  }

  salvar($event: IProduto) {
		this.produtoService.salvarProduto($event)
			.then(() => {
				this.routeService.navigate('/produto/lista');
			})
			.catch(res => {
				this.error = res.error;
			});
	}
}
