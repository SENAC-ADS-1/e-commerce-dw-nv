import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICategoria } from 'src/app/modules/categoria/model/categoria.model';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-produto-atualizar',
  templateUrl: './produto-atualizar.component.html',
  styleUrls: ['./produto-atualizar.component.scss']
})
export class ProdutoAtualizarComponent implements OnInit {

  constructor(
    private produtoService: ProdutoService,
    private activatedRoute: ActivatedRoute,
    private categoriaService: CategoriaService,
    private routeService: RouteService
  ) { }
  
  produto = {} as IProduto;
  categoria = {} as ICategoria;
  categorias: ICategoria[] = [];
  error = {} as any;

  ngOnInit(): void {
    this.getOne(this.activatedRoute.snapshot.params.idProduto);
  
    this.categoriaService
        .getFullCategoria()
        .then(res => {
          console.log(res)
          this.categorias = res;
          console.log(this.categorias)
        });
  }

  getOne(id: number) {
    this.produtoService.getOne(id)
      .then(res => {
        console.log(res);
        this.produto = res;
        this.categoria = this.produto.categoria;
      })
  }

  salvar($event: IProduto) {
		this.produtoService.atualizarProduto($event)
			.then(() => {
				this.routeService.navigate('/produto/lista');
			})
			.catch(res => {
				this.error = res.error;
			});
	}

}
