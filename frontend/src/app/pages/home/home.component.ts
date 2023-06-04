import {Component} from '@angular/core';
import {QuartoModel} from "../../shared/models/quarto.model";
import {QuartoService} from "../../shared/services/quarto.service";
import {ReservaModel} from "../../shared/models/reserva.model";
import {ReservaService} from "../../shared/services/reserva.service";

@Component({
	templateUrl: 'home.component.html',
	styleUrls: ['./home.component.scss']
})

export class HomeComponent {
	loadingVisible: boolean = false;
	quartosAExibir: {quarto: QuartoModel, reserva: ReservaModel | null}[] = [];

	constructor(private quartoService: QuartoService,
				private reservaService: ReservaService) {
	}

	ngOnInit() {
		this.loadingVisible = true;
		this.quartoService.getQuartos().subscribe(
			resp => {
				if (resp.status === 200) {
					resp.body!.forEach(q => {
						if (q.ativo) {
							this.quartosAExibir.push({quarto: q, reserva: null})
						}
					})
				}
				this.reservaService.getReservas().subscribe(
					resp => {
						let hoje = new Date();

						for (let r of resp) {
							for (let q of this.quartosAExibir) {
								if (r.quarto.id === q.quarto.id && r.dataEntrada <= hoje && r.dataSaida >= hoje) {
                                    q.reserva = r;
                                }
							}
						}
						this.loadingVisible = false;
					}
				);
			}
		);
	}
}
