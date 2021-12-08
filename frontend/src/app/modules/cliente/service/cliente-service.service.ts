import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { IPageConfig } from 'src/app/commons/models/pagination.model';
import { ICliente } from '../model/cliente.model';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private httpClient: HttpClient) { }

  getAll(): Promise<IPageConfig<ICliente>> {
    return this.httpClient
      .get<IPageConfig<ICliente>>('http://localhost:8080/cliente/')
      .toPromise();
  }

  getOne(id: number): Promise<ICliente> {
    return this.httpClient
      .get<ICliente>(`http://localhost:8080/cliente/${id}`)
      .toPromise();
  }

  removerCliente(cliente: ICliente) {
    return this.httpClient
      .delete<ICliente>(`http://localhost:8080/cliente/${cliente.id}`)
      .toPromise();
  }

  salvarCliente(cliente: ICliente): Promise<ICliente> {
    return this.httpClient
      .post<ICliente>('http://localhost:8080/cliente/', cliente)
      .toPromise();
  }

}
