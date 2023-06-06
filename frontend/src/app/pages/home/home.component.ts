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

	selecionarQuarto(quarto: any) {
		if (this.quartoSelecionado === quarto) {
			this.quartoSelecionado = null;
			return;
		}
		this.quartoSelecionado = quarto;
	}

	quartoSelecionado: any = null;
	checkInPopupVisible: boolean = false;

	realizarCheckIn() {
		if (!this.quartoSelecionado) {
			notify('Selecione um quarto para realizar o check-in', 'warning', 3000);
			return;
		}
		if (this.quartoSelecionado.reserva.checkIn) {
			notify('Já foi realizado o check-in para essa reserva', 'warning', 3000);
			return;
		}
		this.checkInPopupVisible = true;
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

	cpfFormatado(cpf): string {
		if (cpf) {
			return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
		} else {
			return cpf
		}
	}

	confirmarCheckIn() {
		this.quartoSelecionado.reserva.checkIn = true;
		this.reservaService.updateReserva(this.quartoSelecionado.reserva).subscribe(resp => {
			if (resp.status === 200) {
				notify('Check-in realizado com sucesso', 'success', 3000);
				this.checkInPopupVisible = false;
			}
		});
	}
}
