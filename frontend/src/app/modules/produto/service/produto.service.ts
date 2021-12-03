import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { IProduto } from '../model/produto.model';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Promise<IPageConfig<IProduto>> {
    return this.httpClient
      .get<IPageConfig<IProduto>>('http://localhost:8080/produto/')
      .toPromise();
  }

  getOne(id: number): Promise<IProduto> {
    return this.httpClient
      .get<IProduto>(`http://localhost:8080/produto/${id}`)
      .toPromise();
  }
}
