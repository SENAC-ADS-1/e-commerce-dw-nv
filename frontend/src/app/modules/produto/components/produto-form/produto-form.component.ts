import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ICategoria } from 'src/app/modules/categoria/model/categoria.model';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';

@Component({
  selector: 'app-produto-form',
  templateUrl: './produto-form.component.html',
  styleUrls: ['./produto-form.component.scss']
})
export class ProdutoFormComponent implements OnInit {

  constructor(
    private categoriaService: CategoriaService,
    private location: Location
  ) { }

  @Input()
  produto = {} as IProduto;
  @Input()
  categorias: ICategoria[] = [];  
  @Input()
  isVisualizar: boolean = false;
  @Input()
  tipoForm?: number;

  @Output()
  private salvarProdutoEventPublisher = new EventEmitter<IProduto>();

  ngOnInit(): void { 

  }

  salvar() {
    this.salvarProdutoEventPublisher.emit(this.produto);
  }

  getCategorias() {
    this.categoriaService
      .getFullCategoria()
      .then(res => {
        this.categorias = res;
      })
  }

  goBack() {
    this.location.back();
  }

}
