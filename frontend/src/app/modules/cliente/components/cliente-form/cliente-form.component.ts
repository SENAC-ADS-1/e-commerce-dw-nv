import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ICliente } from '../../model/cliente.model';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.scss']
})
export class ClienteFormComponent implements OnInit {

  constructor(private location: Location) { }

  @Input()
  cliente = {} as ICliente;
  
  @Input()
  isAtualizarData: boolean = false;

  @Output()
  private salvarClienteEventPublisher = new EventEmitter<ICliente>();

  ngOnInit(): void {
  }

  goBack() {
    this.location.back();
  }

  salvar() {
    this.salvarClienteEventPublisher.emit(this.cliente);
  }

}
