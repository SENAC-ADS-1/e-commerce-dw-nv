import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { ClienteAtualizarSenhaComponent } from './pages/cliente-atualizar-senha/cliente-atualizar-senha.component';
import { ClienteAtualizarComponent } from './pages/cliente-atualizar/cliente-atualizar.component';
import { ClienteCadastrarComponent } from './pages/cliente-cadastrar/cliente-cadastrar.component';
import { ClienteListaComponent } from './pages/cliente-lista/cliente-lista.component';

@Component({
  template: '<router-outlet></router-outlet>'
})
export class GenericRouterComponent { };

const routes: Routes = [
  {
    path: 'cliente',
    children: [
      { path: '', redirectTo: '/cliente/lista', pathMatch: 'full' },
      { path: 'lista', component: ClienteListaComponent },
      { path: 'cadastrar', component:  ClienteCadastrarComponent },
      { path: 'atualizar/:idCliente/data', component: ClienteAtualizarComponent },
      { path: 'atualizar/:idCliente/password', component: ClienteAtualizarSenhaComponent },
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
export class ClienteRoutingModule { };