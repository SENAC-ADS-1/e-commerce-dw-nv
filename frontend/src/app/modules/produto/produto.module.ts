import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ProdutoListaComponent } from './pages/produto-lista/produto-lista.component';
import { GenericRouterComponent, ProdutoRoutingModule } from './produto-routing.module';
// import { ProdutoVisualizarComponent } from './pages/produto-visualizar/produto-visualizar.component';
import { ProdutoFormComponent } from './components/produto-form/produto-form.component';
import { ProdutoCadastrarComponent } from './pages/produto-cadastrar/produto-cadastrar.component';
import { ProdutoAtualizarComponent } from './pages/produto-atualizar/produto-atualizar.component';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    GenericRouterComponent,
    ProdutoListaComponent,
    // ProdutoVisualizarComponent,
    ProdutoFormComponent,
    ProdutoCadastrarComponent,
    ProdutoAtualizarComponent
  ],
  imports: [
    CommonModule,
    ProdutoRoutingModule,
    FormsModule
  ]
})
export class ProdutoModule { };