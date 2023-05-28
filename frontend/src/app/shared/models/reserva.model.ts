import {QuartoModel} from "./quarto.model";
import {HospedeModel} from "./hospede.model";

export class ReservaModel {
	id?: number;
	dataEntrada: Date;
	dataSaida: Date;
	quarto: QuartoModel;
	hospedes: HospedeModel[];
	observacao: string;
}
