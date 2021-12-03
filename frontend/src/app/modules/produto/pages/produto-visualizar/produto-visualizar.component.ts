import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private _location: Location
  ) { }
  
  produto = {} as IProduto;

  ngOnInit(): void {
    this.getOne(this.activatedRoute.snapshot.params.idProduto);
  }

  historyBack() {
    console.log('banana')
    this._location.back();
  }

  getOne(id: number) {
    this.produtoService.getOne(id)
      .then(res => {
        console.log(res);
        this.produto = res;
      })
  }

}
