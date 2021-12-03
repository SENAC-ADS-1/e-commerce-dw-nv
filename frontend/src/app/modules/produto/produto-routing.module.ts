import { Component, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { ProdutoListaComponent } from './pages/produto-lista/produto-lista.component';
import { ProdutoVisualizarComponent } from './pages/produto-visualizar/produto-visualizar.component';

@Component({
  template: '<router-outlet></router-outlet>'
})

export class GenericRouterComponent { };

const routes: Routes = [
  {
    path: 'produto',
    children: [
      { path: '', redirectTo: '/produto/lista', pathMatch: 'full' },
      { path: 'lista', component: ProdutoListaComponent },
      { path: 'visualizar/:idProduto', component: ProdutoVisualizarComponent }
    ]
  }
]

@NgModule({
  imports: [
    BrowserModule,
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})

export class ProdutoRoutingModule { };