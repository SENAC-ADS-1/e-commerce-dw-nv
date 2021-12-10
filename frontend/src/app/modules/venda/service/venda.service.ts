import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { IVenda } from '../pages/model/venda.model';

@Injectable({
  providedIn: 'root'
})
export class VendaService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Promise<IPageConfig<IVenda>> {
    return this.httpClient
      .get<IPageConfig<IVenda>>('http://localhost:8080/venda/')
      .toPromise();
  }
}
