import { ICategoria } from '../../categoria/model/categoria.model';

export interface IProduto {
  id: number;
  nome: string;
  descricao: string;
  complemento?: string;
  valor: number;
  unidadeMedida: string;
  qtde: number;
  fabricante: string;
  fornecedor?: string;
  categoria: ICategoria;
}