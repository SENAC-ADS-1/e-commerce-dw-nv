export interface IProduto {
  id: number;
  nome: string;
  descricao: string;
  complemento?: string;
  valor: number;
  unidadeMedida: string;
  quantidade: number;
  fabricante: string;
  fornecedor?: string;
  categoria: number;
}