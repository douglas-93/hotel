import {ProdutoModel} from "./produto.model";

export class CupomConsumoModel {
	id: number;
	valorDiariaHospede: number;
	reservaId: number;
	dataEmissao: Date;
	dataEntrada: Date;
	dataSaida: Date;
	fechado: boolean;
	produtosConsumidos: ProdutoModel[];
}
