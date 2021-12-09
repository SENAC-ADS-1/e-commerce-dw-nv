import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICliente } from '../../model/cliente.model';
import { ClienteService } from '../../service/cliente.service';

@Component({
  selector: 'app-cliente-atualizar',
  templateUrl: './cliente-atualizar.component.html',
  styleUrls: ['./cliente-atualizar.component.scss']
})
export class ClienteAtualizarComponent implements OnInit {

  constructor(
    private clienteService: ClienteService,
    private activatedRoute: ActivatedRoute,
		private routeService: RouteService
  ) { }

  cliente = { } as ICliente;
  error = {} as any;

  ngOnInit(): void {
    this.getOne(this.activatedRoute.snapshot.params.idCliente);
  }

  getOne(id: number) {
		this.clienteService.getOne(id)
			.then(result => {
				this.cliente = result;
			})
	}

  salvar($event: ICliente) {
		this.clienteService.atualizarCliente($event)
			.then(() => {
				this.routeService.navigate('/cliente/lista');
			})
			.catch(err => {
				this.error = err.error;
			});
	}

}
