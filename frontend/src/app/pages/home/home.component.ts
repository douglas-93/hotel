import {Component} from '@angular/core';
import {QuartoModel} from "../../shared/models/quarto.model";
import {QuartoService} from "../../shared/services/quarto.service";
import {ReservaModel} from "../../shared/models/reserva.model";
import {ReservaService} from "../../shared/services/reserva.service";
import notify from "devextreme/ui/notify";
import * as _ from 'lodash';
import {forkJoin} from "rxjs";

@Component({
	templateUrl: 'home.component.html',
	styleUrls: ['./home.component.scss']
})

export class HomeComponent {
	loadingVisible: boolean = false;
	quartosAExibir: { quarto: QuartoModel, reserva: ReservaModel | null }[] = [];
	quartoSelecionado: any = null;
	checkInPopupVisible: boolean = false;

	constructor(private quartoService: QuartoService,
				private reservaService: ReservaService) {
	}

	ngOnInit() {
		this.buscaQuartosEReservas();
	}

	buscaQuartosEReservas() {
		this.quartosAExibir = [];
		this.loadingVisible = true;

		forkJoin([
			this.quartoService.getQuartos(),
			this.reservaService.getReservas()
		]).subscribe(([quartosResp, reservasResp]) => {
			this.quartosAExibir = quartosResp.body!.filter(q => q.ativo).map(q => ({quarto: q, reserva: null}));

			let hoje = new Date();

			_.forEach(reservasResp, r => {
				let quarto = this.quartosAExibir.find(q => r.quarto.id === q.quarto.id && r.dataEntrada <= hoje && r.dataSaida >= hoje);
				if (quarto) {
					quarto.reserva = r;
				}
			});

			this.loadingVisible = false;
		});
	}

	selecionarQuarto(quarto: any) {
		if (this.quartoSelecionado === quarto) {
			this.quartoSelecionado = null;
			return;
		}
		this.quartoSelecionado = quarto;
	}

	realizarCheckIn() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para realizar o check-in', 'warning', 3000);
			return;
		}
		if (!this.quartoSelecionado.reserva) {
			notify('O quarto não possui reserva', 'warning', 3000);
			return;
		}
		if (this.quartoSelecionado.reserva.checkedIn) {
			notify('Já foi realizado o check-in para essa reserva', 'warning', 3000);
			return;
		}
		this.checkInPopupVisible = true;
	}

	realizarCheckOut() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para realizar o check-out', 'warning', 3000);
			return;
		}
		if (!this.quartoSelecionado.reserva) {
			notify('O quarto não possui reserva', 'warning', 3000);
			return;
		}
		if (!this.quartoSelecionado.reserva.checkedIn) {
			notify('Check-in ainda não realizado para essa reserva', 'warning', 3000);
			return;
		}
		console.log('Realizar Check-Out:', this.quartoSelecionado);
	}

	gerenciarConsumo() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para gerenciar o consumo', 'warning', 3000);
			return;
		}
		console.log('Gerenciar Consumo:', this.quartoSelecionado);
	}

	cpfFormatado(cpf): string {
		if (cpf) {
			return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
		} else {
			return cpf
		}
	}

	defineDataSaida(e: any) {
		this.quartoSelecionado.reserva.dataSaida = <Date>e
	}

	confirmarCheckIn() {
		this.reservaService.fazerCheckIn(this.quartoSelecionado.reserva)
			.subscribe(resp => {
					if (resp.status === 200) {
						notify('Check-in realizado com sucesso', 'success', 3000);
						this.checkInPopupVisible = false;
						this.buscaQuartosEReservas();
					}
				},
				error => {
					if (error.status === 409) {
						notify(error.error.message, 'warning', 5000);
					}
				});
	}
}
