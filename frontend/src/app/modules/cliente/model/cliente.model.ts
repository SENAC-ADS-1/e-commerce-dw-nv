export interface ICliente {
  id?: number;
  nomeCompleto: string;
  usuario: string;
  senha?: string;
  dataNascimento: string;
}