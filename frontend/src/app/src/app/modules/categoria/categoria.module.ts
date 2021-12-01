import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { CategoriaRoutingModule, GenericRouterComponent } from './categoria-routing.module';
import { CategoriaListarComponent } from './pages/categoria-listar/categoria-listar.component';

@NgModule({
  declarations: [
    GenericRouterComponent,
    CategoriaListarComponent
  ],
  imports: [
    CommonModule,
    CategoriaRoutingModule
  ]
})
export class CategoriaModule { };