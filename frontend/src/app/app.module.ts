import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { CategoriaModule } from './modules/categoria/categoria.module';
import { CategoriaListaComponent } from './modules/categoria/pages/categoria-lista/categoria-lista.component';
import { ProdutoListaComponent } from './modules/produto/pages/produto-lista/produto-lista.component';
import { ProdutoModule } from './modules/produto/produto.module';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ClienteListaComponent } from './modules/cliente/pages/cliente-lista/cliente-lista.component';
import { ClienteModule } from './modules/cliente/cliente.module';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    CategoriaModule,
    ProdutoModule,
    ClienteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
