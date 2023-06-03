import {ChangeDetectorRef, Component, NgModule, ViewChild} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";
import {
	DxAccordionModule,
	DxAutocompleteComponent,
	DxAutocompleteModule,
	DxButtonModule,
	DxDataGridModule,
	DxDateBoxModule,
	DxSelectBoxComponent,
	DxSelectBoxModule,
	DxTextAreaModule,
	DxToastModule
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
import {Router} from "@angular/router";

@Component({
	selector: 'app-reserva-form',
	templateUrl: './reserva-form.component.html',
	styleUrls: ['./reserva-form.component.scss']
})
export class ReservaFormComponent {

	@ViewChild('autComp')
	autComp: DxAutocompleteComponent
	@ViewChild('quarto', {static: false})
	quarto: DxSelectBoxComponent
	reserva: ReservaModel = new ReservaModel();
	hospedesNaReserva: HospedeModel[] = [];
	quartos: QuartoModel[] = [];
	hospedeSelecinado: HospedeModel;
	hospedes: HospedeModel[];
	isUpdate: boolean = false;

	constructor(private hospService: HospedeService,
				private quartSerice: QuartoService,
				private reservService: ReservaService,
				private router: Router,
				private cdr: ChangeDetectorRef) {
	}

	ngOnInit() {
		this.quartSerice.getQuartos().subscribe(resp => {
			if (resp.status === 200) {
				this.quartos = resp.body!.filter(q => q.ativo === true)
			}
		})

		this.hospService.getHospedes().subscribe(resp => {
			this.hospedes = resp
		})

		let id = this.router.url.split('/').pop()
		if (id?.match(/[0-9]+/)) {
			this.isUpdate = true
			this.reservService.getReserva(Number.parseInt(id)).subscribe(resp => {
				if (resp.status === 200) {
					this.reserva = resp.body!
					this.hospedesNaReserva = this.reserva.hospedes
					setTimeout(() => {
						const quartoItem = this.quartos.find(q => q.id === this.reserva.quarto.id);
						if (quartoItem) {
							this.quarto.instance.option('value', quartoItem);
							this.cdr.detectChanges();
						}
					}, 500)
				}
			})
		}
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

	mostraMensagem(tipo: string, mensagem: string, tempo = 3500) {
		notify({
				message: `${mensagem}`,
				icon: 'clear',
				type: tipo,
				displayTime: tempo,
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
		this.reserva.hospedes = this.hospedesNaReserva
		if (this.reserva.dataSaida < this.reserva.dataEntrada) {
			this.mostraMensagem('error', 'Data final não pode ser menor que data incial')
		} else {
			if (this.validaReserva()) {
				if (this.isUpdate) {
					this.atualiza()
				} else {
					this.cria()
				}
			}
		}
	}

	cria() {
		this.reservService.createReserva(this.reserva)
			.subscribe(
				resp => {
					if (resp.status === 201) {
						this.mostraMensagem('success', 'Reserva realizada com sucesso');
						setTimeout(this.voltar, 1000);
					}
				},
				error => {
					if (error.status === 409) {
						this.mostraMensagem('warning', error.error.message, 5000);
					}
				}
			);
	}

	atualiza() {
		this.reservService.updateReserva(this.reserva).subscribe(resp => {
			if (resp.status === 200) {
				this.mostraMensagem('success', 'Reserva atualizada com sucesso')
				setTimeout(this.voltar, 1000)
			}
		})
	}

	defineDataInicial(e: Date | number | string) {
		this.reserva.dataEntrada = <Date>e
	}

	defineDataFinal(e: Date | number | string) {
		this.reserva.dataSaida = <Date>e
	}

	defineQuarto() {
		this.reserva.quarto = this.quarto.selectedItem
	}

	voltar() {
		window.history.back()
	}

	validaReserva() {
		let erros = 0;

		if (this.reserva.hospedes === null || this.reserva.hospedes.length === 0) {
			erros++;
			this.mostraMensagem('error', 'Adicione pelo menos um hospede');
		}

		if (this.reserva.dataEntrada === null || this.reserva.dataEntrada === undefined
			|| this.reserva.dataSaida === undefined || this.reserva.dataSaida === null) {
			erros++;
			this.mostraMensagem('error', 'Defina a data de entrada e saída');
		}

		if (this.reserva.quarto === null || this.reserva.quarto === undefined) {
			erros++;
			this.mostraMensagem('error', 'Defina um quarto');
		}

		return erros === 0;
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
		FormsModule,
		DxTextAreaModule,
		DxToastModule
	],
	declarations: [ReservaFormComponent],
	exports: [ReservaFormComponent]
})
export class ReservaFormModule {
}
