import { Component, OnInit } from '@angular/core';
import { RouteService } from 'src/app/commons/services/route.service';
import { ClienteService } from 'src/app/modules/cliente/service/cliente.service';
import { ProdutoService } from 'src/app/modules/produto/service/produto.service';

@Component({
  selector: 'app-venda-lista',
  templateUrl: './venda-lista.component.html',
  styleUrls: ['./venda-lista.component.scss']
})
export class VendaListaComponent implements OnInit {

  constructor(
    private routeService: RouteService,
    private clienteService: ClienteService,
    private produtoService: ProdutoService
    //private enderecoService: EnderecoService
  ) { }

  ngOnInit(): void {
  }

}
