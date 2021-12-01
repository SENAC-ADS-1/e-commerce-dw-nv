import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { CategoriaListarComponent } from './pages/categoria-listar/categoria-listar.component';

@Component({
  template: '<router-outlet></router-outlet>',
})
export class GenericRouterComponent {};

const routes: Routes = [
  {
    path: 'categoria',
    children: [
      { path: '', redirectTo: '/categoria/lista', pathMatch: 'full' },
      { path: 'lista', component: CategoriaListarComponent }
    ]
  }
];

@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forChild(routes),
  ],
  exports: [
    RouterModule  
  ]
})
export class CategoriaRoutingModule {};