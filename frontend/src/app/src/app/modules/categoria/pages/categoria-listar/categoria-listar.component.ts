import { Component, OnInit } from '@angular/core';
import { CategoriaService } from '../../service/categoria.service';

@Component({
  selector: 'app-categoria-listar',
  templateUrl: './categoria-listar.component.html',
  styleUrls: ['./categoria-listar.component.scss']
})

export class CategoriaListarComponent implements OnInit {
  categorias: any[] = [];

  constructor(private categoriaService: CategoriaService) { }

  ngOnInit(): void {
    this.getAll();
  }

  getAll() {
    this.categoriaService
      .getAll()
      .then(res => {
        this.categorias = res.content;
      })
      .catch(err => { console.log('Erro: ', err) })
  }
}
