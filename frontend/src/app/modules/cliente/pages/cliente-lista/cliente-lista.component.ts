import { Component, OnInit } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICliente } from '../../model/cliente.model';
import { ClienteService } from '../../service/cliente.service';

@Component({
  selector: 'app-cliente-lista',
  templateUrl: './cliente-lista.component.html',
  styleUrls: ['./cliente-lista.component.scss']
})
export class ClienteListaComponent implements OnInit {
  clientes = { } as IPageConfig<ICliente>;

  constructor(
    private routeService: RouteService,
    private clienteService: ClienteService
  ) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.clienteService
      .getAll()
      .then(res => {
        this.clientes = res;
        console.log('cliente: ', this.clientes)
      })
      .catch(err => { console.log('Erro cliente: ', err) });
  }

  cadastrarNovoCliente() {
    this.routeService.navigate(`/cliente/cadastrar`);
  }

  remover(cliente: any) {
    this.clienteService.removerCliente(cliente).then(() => this.getAll());
  }

  atualizar(cliente: any) {
    this.routeService.navigate(`cliente/atualizar/${cliente.id}/data`);
  }

  atualizarSenha(cliente: any) {
    this.routeService.navigate(`cliente/atualizar/${cliente.id}/password`);
  }

}
