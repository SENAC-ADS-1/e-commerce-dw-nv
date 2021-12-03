import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ProdutoListaComponent } from './pages/produto-lista/produto-lista.component';
import { GenericRouterComponent, ProdutoRoutingModule } from './produto-routing.module';
import { ProdutoVisualizarComponent } from './pages/produto-visualizar/produto-visualizar.component';

@NgModule({
  declarations: [
    GenericRouterComponent,
    ProdutoListaComponent,
    ProdutoVisualizarComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule
  ]
})
export class ProdutoModule { };