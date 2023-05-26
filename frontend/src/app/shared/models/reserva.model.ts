import {QuartoModel} from "./quarto.model";
import {HospedeModel} from "./hospede.model";

export class ReservaModel {
	id?: number;
	hospede: HospedeModel[];
	quarto: QuartoModel;
	dataDeRealizacaoDaReserva: Date = new Date();
	inicio: Date;
	fim: Date;
}
