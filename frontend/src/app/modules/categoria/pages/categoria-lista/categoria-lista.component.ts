import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria.service';
import { RouteService } from 'src/app/commons/services/route.service';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { ICategoria } from '../../model/categoria.model';

@Component({
	selector: 'app-categoria-lista',
	templateUrl: './categoria-lista.component.html',
	styleUrls: ['./categoria-lista.component.css']
})
export class CategoriaListaComponent implements OnInit {

	categorias = {} as IPageConfig<ICategoria>;

	constructor(private categoriaService: CategoriaService,
				private routeService: RouteService) { }

	ngOnInit(): void {
		this.getAll();
	}

	getAll() {
		this.categoriaService
			.getAll()
			.then(
				result => {
					this.categorias = result;
				}
			)
			.catch((err) => { console.log('Erro: ', err) });
	}

	visualizar(categoria: any) {
		this.routeService.navigate(`categoria/visualizar/${categoria.id}`);
	}

	atualizar(categoria: any) {
		this.routeService.navigate(`categoria/atualizar/${categoria.id}`);
	}

	remover(categoria: any) {
		this.categoriaService.removerCategoria(categoria).then(() => this.getAll());
	}

	cadastrarNovaCategoria() {
		this.routeService.navigate(`/categoria/cadastrar`);
	}
}
