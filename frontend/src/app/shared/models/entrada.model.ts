import {ProdutoModel} from "./produto.model";

export class EntradaModel {
	id: number;
	produto: ProdutoModel;
	quantidade: number;
	tipo: string;
	data: Date;
}
