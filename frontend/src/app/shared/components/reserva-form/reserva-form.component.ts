import {Component, NgModule, ViewChild} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";
import {
	DxAccordionModule, DxAutocompleteComponent, DxAutocompleteModule,
	DxButtonModule,
	DxDataGridModule,
	DxDateBoxModule,
	DxSelectBoxModule
} from "devextreme-angular";
import {HospedeModel} from "../../models/hospede.model";
import notify from "devextreme/ui/notify";
import {ReservaModel} from "../../models/reserva.model";
import {DatePipe} from "@angular/common";
import {QuartoModel} from "../../models/quarto.model";
import {HospedeService} from "../../services/hospede.service";
import {QuartoService} from "../../services/quarto.service";
import {ReservaService} from "../../services/reserva.service";
import {FormsModule} from "@angular/forms";

@Component({
	selector: 'app-reserva-form',
	templateUrl: './reserva-form.component.html',
	styleUrls: ['./reserva-form.component.scss']
})
export class ReservaFormComponent {

	@ViewChild('autComp')
	autComp: DxAutocompleteComponent
	reserva: ReservaModel = new ReservaModel();
	hospedesNaReserva: HospedeModel[] = [];
	quartos: QuartoModel[] = [];
	hospedeSelecinado: HospedeModel;
	hospedes: HospedeModel[];

	constructor(private hospService: HospedeService,
				private quartSerice: QuartoService,
				private reservService: ReservaService) {
		this.quartSerice.getQuartos().subscribe(resp => {
			if (resp.status === 200) {
				this.quartos = resp.body!
			}
		})

		this.hospService.getHospedes().subscribe(resp => {
			this.hospedes = resp
			console.log(this.hospedes)
		})
	}

	adicionarHospede() {
		this.hospedesNaReserva.push(this.autComp.selectedItem);
		this.autComp.value = ''
	}

	removerHospede() {
		let index = this.hospedesNaReserva.indexOf(this.hospedeSelecinado)
		if (index != -1) {
			this.hospedesNaReserva.splice(index, 1)
		} else {
			this.mostraMensagem('warning', 'Selecione o hospede a ser removido da reserva')
		}
	}

	selecionaHospede(e) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(hospede => {
			if (hospede) {
				this.hospedeSelecinado = hospede;
			}
		});
	}

	cpfFormatado(cpf): string {
		if (cpf) {
			return cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
		} else {
			return cpf
		}
	}

	mostraMensagem(tipo: string, mensagem: string) {
		notify({
				message: `${mensagem}`,
				icon: 'clear',
				type: tipo,
				displayTime: 3500,
				animation: {
					show: {
						type: 'fade', duration: 400, from: 0, to: 1,
					},
					hide: {type: 'fade', duration: 40, to: 0},
				},
			},
			{position: 'bottom center', direction: "up-push"});
	}

	salvaReserva() {
		this.reserva.hospede = this.hospedesNaReserva
		if (this.reserva.fim < this.reserva.inicio) {
			this.mostraMensagem('error', 'Data final não pode ser menor que data incial')
		}
		console.log(this.reserva)
	}

	defineDataInicial(e: Date | number | string) {
		this.reserva.inicio = <Date>e
	}
	defineDataFinal(e: Date | number | string) {
		this.reserva.fim = <Date>e
	}
}

@NgModule({
	imports: [
		ToolbarModule,
		DxAccordionModule,
		DxButtonModule,
		DxDataGridModule,
		DxDateBoxModule,
		DatePipe,
		DxSelectBoxModule,
		DxAutocompleteModule,
		FormsModule
	],
	declarations: [ReservaFormComponent],
	exports: [ReservaFormComponent]
})
export class ReservaFormModule {
}
