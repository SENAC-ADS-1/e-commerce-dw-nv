import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { RouteService } from 'src/app/commons/services/route.service';
import { ICliente } from '../../model/cliente.model';
import { ClienteService } from '../../service/cliente.service';

@Component({
  selector: 'app-cliente-atualizar-senha',
  templateUrl: './cliente-atualizar-senha.component.html',
  styleUrls: ['./cliente-atualizar-senha.component.scss']
})
export class ClienteAtualizarSenhaComponent implements OnInit {

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
		this.clienteService.atualizarClienteSenha($event)
			.then(() => {
				this.routeService.navigate('/cliente/lista');
			})
			.catch(err => {
				this.error = err.error;
			});
	}
}
