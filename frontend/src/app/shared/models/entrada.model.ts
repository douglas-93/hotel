import {ProdutoModel} from "./produto.model";

export class EntradaModel {
	id: number;
	produto: ProdutoModel;
	quantidade: number;
	nota: string;
	dataEntrada: Date;
}
