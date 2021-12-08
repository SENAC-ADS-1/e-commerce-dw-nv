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

  atualizarProduto(produto: IProduto): Promise<IProduto> {
    return this.httpClient
      .put<IProduto>(`http://localhost:8080/produto/${produto.id}`, produto)
      .toPromise();
  }

  salvarProduto(produto: IProduto): Promise<IProduto> {
    return this.httpClient
      .post<IProduto>(`http://localhost:8080/produto/`, produto)
      .toPromise();
  }

  deletarProduto(produto: IProduto): Promise<IProduto> {
    return this.httpClient
      .delete<IProduto>(`http://localhost:8080/produto/${produto.id}`)
      .toPromise();
  }
}
