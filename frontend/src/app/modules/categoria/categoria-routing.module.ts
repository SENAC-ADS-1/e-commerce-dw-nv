import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { CategoriaAtualizarComponent } from './pages/categoria-atualizar/categoria-atualizar.component';
import { CategoriaCadastrarComponent } from './pages/categoria-cadastrar/categoria-cadastrar.component';
import { CategoriaListaComponent } from './pages/categoria-lista/categoria-lista.component';
import { CategoriaVisualizarComponent } from './pages/categoria-visualizar/categoria-visualizar.component';

@Component({
	template: '<router-outlet></router-outlet>'
})
export class GenericRouterComponent { };

const routes: Routes = [
	{
		path: 'categoria',
		children: [
			{ path: '', redirectTo: '/categoria/lista', pathMatch: 'full' },
			{ path: 'lista', component: CategoriaListaComponent },
			{ path: 'atualizar/:idCategoria', component: CategoriaAtualizarComponent },
			{ path: 'visualizar/:idCategoria', component: CategoriaVisualizarComponent },
			{ path: 'cadastrar', component: CategoriaCadastrarComponent },
		]
	}
];

@NgModule({
	imports: [
		BrowserModule,
		FormsModule,
		RouterModule.forRoot(routes),
	],
	exports: [RouterModule]
})
export class CategoriaRoutingModule { };