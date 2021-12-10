import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { VendaListaComponent } from "./pages/venda-lista/venda-lista.component";
import { GenericRouterComponent, VendaRoutingModule } from "./venda-routing.module";

@NgModule({
  declarations: [
    GenericRouterComponent,
    VendaListaComponent
  ],
  imports: [
    CommonModule,
    VendaRoutingModule,
    FormsModule
  ]
})
export class VendaModule { };