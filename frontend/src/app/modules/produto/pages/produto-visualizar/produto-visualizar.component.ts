import { Location } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { ICategoria } from 'src/app/modules/categoria/model/categoria.model';
import { CategoriaService } from 'src/app/modules/categoria/service/categoria.service';
import { IProduto } from '../../model/produto.model';
import { ProdutoService } from '../../service/produto.service';

@Component({
  selector: 'app-produto-visualizar',
  templateUrl: './produto-visualizar.component.html',
  styleUrls: ['./produto-visualizar.component.scss']
})
export class ProdutoVisualizarComponent implements OnInit {

  constructor(
    private produtoService: ProdutoService,
    private activatedRoute: ActivatedRoute,
    private categoriaService: CategoriaService
  ) { }
  
  produto = {} as IProduto;
  categoria = {} as ICategoria;

  ngOnInit(): void {
    this.getOne(this.activatedRoute.snapshot.params.idProduto);
  }

  getOne(id: number) {
    this.produtoService.getOne(id)
      .then(res => {
        console.log(res);
        this.produto = res;
        this.categoria = this.produto.categoria;
      })
  }

}
