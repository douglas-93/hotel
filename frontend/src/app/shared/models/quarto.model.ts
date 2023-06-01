import {TiposQuartoEnum} from "../enums/tipos-quartos.enum";
import {CategoriasEnum} from "../enums/categorias.enum";

export class QuartoModel {
	id?: number;
	nome: string;
	tipo: TiposQuartoEnum;
	categoria: CategoriasEnum;
	ativo: boolean | undefined | null = true;
	imagem: File | null;
	imagemURL: string | null;
}
