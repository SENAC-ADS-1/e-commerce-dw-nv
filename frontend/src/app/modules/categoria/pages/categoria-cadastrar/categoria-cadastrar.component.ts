import { Component, OnInit } from '@angular/core';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICadastroAtualizacaoCategoria, ICategoria } from '../../model/categoria.model';
import { CategoriaService } from '../../service/categoria.service';

@Component({
	selector: 'app-categoria-cadastrar',
	templateUrl: './categoria-cadastrar.component.html',
	styleUrls: ['./categoria-cadastrar.component.css']
})
export class CategoriaCadastrarComponent implements OnInit {

	constructor(private categoriaService: CategoriaService,
		private routeService: RouteService) { }

	categoria = {} as ICadastroAtualizacaoCategoria;
	error = {} as any;

	ngOnInit(): void {
		this.limpaErro();
	}

	limpaErro() {
		this.error.message = '';
	}

	salvar($event: ICategoria) {
		this.categoriaService.salvarCategoria($event)
			.then(() => {
				this.routeService.navigate('/categoria/lista');
			})
			.catch(res => {
				this.error = res.error;
			});
	}
}