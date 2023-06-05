import {Component, EventEmitter, Output} from '@angular/core';
import {QuartoModel} from "../../shared/models/quarto.model";
import {QuartoService} from "../../shared/services/quarto.service";
import {ReservaModel} from "../../shared/models/reserva.model";
import {ReservaService} from "../../shared/services/reserva.service";
import notify from "devextreme/ui/notify";

@Component({
	templateUrl: 'home.component.html',
	styleUrls: ['./home.component.scss']
})

export class HomeComponent {
	loadingVisible: boolean = false;
	quartosAExibir: { quarto: QuartoModel, reserva: ReservaModel | null }[] = [];
	@Output() menuItemClick: EventEmitter<any> = new EventEmitter<any>();

	items = [{
		text: 'Share',
		icon: 'dx-icon-globe',
		items: [
			{text: 'Facebook'},
			{text: 'Twitter'}],
	},
		{text: 'Check-In', icon: 'dx-icon-check'},
		{text: 'Check-Out', icon: 'dx-icon-favorites'},
		{text: 'Consumo', icon: 'dx-icon-add'},
	];

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

	//{ quarto: QuartoModel; reserva: ReservaModel }
	selecionarQuarto(quarto: any) {
		console.log(quarto)
		if (this.quartoSelecionado === quarto.quarto) {
			this.quartoSelecionado = null;
			return;
		}
		this.quartoSelecionado = quarto.quarto;
	}

	quartoSelecionado: QuartoModel | null = null;

	realizarCheckIn() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para realizar o check-in', 'warning', 3000);
			return;
		}
		console.log('Realizar Check-In:', this.quartoSelecionado);
	}

	realizarCheckOut() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para realizar o check-out', 'warning', 3000);
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
}
