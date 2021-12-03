import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CategoriaRoutingModule, GenericRouterComponent } from './categoria-routing.module';
import { FormsModule } from '@angular/forms';
import { CategoriaListaComponent } from './pages/categoria-lista/categoria-lista.component';
import { CategoriaVisualizarComponent } from './pages/categoria-visualizar/categoria-visualizar.component';
import { CategoriaCadastrarComponent } from './pages/categoria-cadastrar/categoria-cadastrar.component';
import { CategoriaAtualizarComponent } from './pages/categoria-atualizar/categoria-atualizar.component';
import { CategoriaFormComponent } from './components/categoria-form/categoria-form.component';

@NgModule({
	declarations: [
		GenericRouterComponent,
		CategoriaListaComponent,
		CategoriaVisualizarComponent,
		CategoriaCadastrarComponent,
		CategoriaAtualizarComponent,
		CategoriaFormComponent
	],
	imports: [
		CommonModule,
		CategoriaRoutingModule,
		FormsModule
	]
})
export class CategoriaModule { };
