import { File } from "./file.models";

export interface Diretorio {
    id: number;
    nome: string;
    subDiretorios: Diretorio[];
    files: File[];
  }