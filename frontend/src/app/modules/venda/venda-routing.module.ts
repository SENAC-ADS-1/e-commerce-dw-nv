import { Component, NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserModule } from "@angular/platform-browser";
import { RouterModule, Routes } from "@angular/router";
import { VendaListaComponent } from "./pages/venda-lista/venda-lista.component";

@Component({
  template: '<router-outlet></router-outlet'
})
export class GenericRouterComponent {};

const routes: Routes = [
  {
    path: 'venda',
    children: [
      { path: '', redirectTo: '/venda/lista', pathMatch: 'full' },
      { path: 'lista', component: VendaListaComponent }
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
export class VendaRoutingModule { };