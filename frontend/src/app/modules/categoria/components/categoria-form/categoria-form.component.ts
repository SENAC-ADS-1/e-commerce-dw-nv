import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ICategoria } from '../../model/categoria.model';

@Component({
	selector: 'app-categoria-form',
	templateUrl: './categoria-form.component.html',
	styleUrls: ['./categoria-form.component.css']
})
export class CategoriaFormComponent implements OnInit {

	constructor(private location: Location) { }

	@Input()
	categoria = {} as ICategoria;

	@Output()
	private salvarCategoriaEventPublisher = new EventEmitter<ICategoria>();

	ngOnInit(): void {
		
	}

	salvar() {
		this.salvarCategoriaEventPublisher.emit(this.categoria);
	}

	limpaErro() {

	}

	disabled() {
		return !this.categoria.descricao || this.categoria.descricao.length >= 30;
	}

  goBack() {
    this.location.back();
  }

}
