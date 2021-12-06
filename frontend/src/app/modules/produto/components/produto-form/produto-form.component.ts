import { Location } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { ICategoria } from 'src/app/modules/categoria/model/categoria.model';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';

@Component({
  selector: 'app-produto-form',
  templateUrl: './produto-form.component.html',
  styleUrls: ['./produto-form.component.scss']
})
export class ProdutoFormComponent implements OnInit {
  categorias = {} as IPageConfig<ICategoria>;

  constructor(
    private categoriaService: CategoriaService,
    private location: Location
  ) { }

  @Input()
  categoria = {} as ICategoria;
  @Input()
  produto = {} as IProduto;

  @Input()
  isVisualizar: boolean = false;

  @Output()
  private salvarProdutoEventPublisher = new EventEmitter<IProduto>();

  ngOnInit(): void { }

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
