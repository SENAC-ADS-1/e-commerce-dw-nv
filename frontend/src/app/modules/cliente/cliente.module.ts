import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ClienteRoutingModule, GenericRouterComponent } from './cliente-routing.module';
import { ClienteListaComponent } from './pages/cliente-lista/cliente-lista.component';
import { ClienteCadastrarComponent } from './pages/cliente-cadastrar/cliente-cadastrar.component';
import { ClienteFormComponent } from './components/cliente-form/cliente-form.component';
import { ClienteAtualizarComponent } from './pages/cliente-atualizar/cliente-atualizar.component';
import { ClienteAtualizarSenhaComponent } from './pages/cliente-atualizar-senha/cliente-atualizar-senha.component';

@NgModule({
  declarations: [
    GenericRouterComponent,
    ClienteListaComponent,
    ClienteCadastrarComponent,
    ClienteFormComponent,
    ClienteAtualizarComponent,
    ClienteAtualizarSenhaComponent,
  ],
  imports: [
    CommonModule,
    ClienteRoutingModule,
    FormsModule
  ]
})
export class ClienteModule { };