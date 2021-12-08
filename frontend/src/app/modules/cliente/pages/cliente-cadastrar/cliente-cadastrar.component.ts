import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICliente } from '../../model/cliente.model';
import { ClienteService } from '../../service/cliente-service.service';

@Component({
  selector: 'app-cliente-cadastrar',
  templateUrl: './cliente-cadastrar.component.html',
  styleUrls: ['./cliente-cadastrar.component.scss']
})
export class ClienteCadastrarComponent implements OnInit {

  constructor(
    private location: Location,
    private routeService: RouteService,
    private clienteService: ClienteService
  ) { }

  error = {} as any;

  ngOnInit(): void {
  }

  salvar($event: ICliente) {
    this.clienteService
      .salvarCliente($event)
      .then(() => {
        this.routeService.navigate('/cliente/lista')
      })
      .catch(err => {
        this.error = err.error;
      })

  }
  

  goBack() {
    this.location.back();
  }

}
