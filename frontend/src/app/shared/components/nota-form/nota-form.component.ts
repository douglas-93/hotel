import {Component, EventEmitter, Input, NgModule, Output, SimpleChanges, ViewChild} from '@angular/core';
import {
	DxButtonModule,
	DxDataGridModule,
	DxLoadPanelModule,
	DxRadioGroupComponent,
	DxRadioGroupModule,
	DxSelectBoxComponent,
	DxSelectBoxModule,
	DxTextBoxModule
} from "devextreme-angular";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {CurrencyPipe, NgIf} from "@angular/common";
import {ProdutoModel} from "../../models/produto.model";
import {forkJoin} from "rxjs";
import {ReservaModel} from "../../models/reserva.model";
import {ReservaService} from "../../services/reserva.service";
import {ProdutoService} from "../../services/produto.service";
import {ConsumoService} from "../../services/consumo.service";
import {ConsumoModel} from "../../models/consumo.model";
import notify from "devextreme/ui/notify";
import {CupomConsumoService} from "../../services/cupomConsumo.service";

@Component({
	selector: 'app-nota-form',
	templateUrl: './nota-form.component.html',
	styleUrls: ['./nota-form.component.scss']
})
export class NotaFormComponent {

	@ViewChild('reserva') reservaSelectBox: DxSelectBoxComponent;
	@ViewChild('tipoDesconto') tipoDesconto: DxRadioGroupComponent;

	@Input() reservaSelecionadaPopUp: number;
	@Output() closePopUpCheckOutEvent = new EventEmitter();

	loadingVisible: boolean = false;
	gerarVisible: boolean = false;
	temDesconto: boolean = false;
	calculado: boolean = false;

	gridData: { produto: ProdutoModel, quantidade: number, dataConsumo: Date, valor: number }[];
	reservas: ReservaModel[];
	produtos: ProdutoModel[];
	datasDaReserva: { data: Date, valor: number }[] = [];
	totalDaNota: number;
	radioOptions: string[] = ['Valor', 'Percentual'];
	totalDaNotaComDesconto: number;
	valorDesconto: string;


	constructor(private reservaService: ReservaService,
				private produtoService: ProdutoService,
				private consumoService: ConsumoService,
				private cupomService: CupomConsumoService) {
	}

	ngOnInit(): void {
		this.loadingVisible = true
		forkJoin([this.reservaService.getReservasHoje(), this.produtoService.getAll(), this.cupomService.getCupons()])
			.subscribe(([resR, resP, resC]) => {
				this.reservas = resR.filter(r => !resC.some(c => c.reservaId === r.id))
				this.produtos = resP.body!

				if (this.reservaSelecionadaPopUp) {
					this.reservaSelectBox.value = this.reservas.find(r => r.id === this.reservaSelecionadaPopUp)
					this.reservaSelectBox.readOnly = true
				}

				this.loadingVisible = false
			});
	}

	ngOnChanges(changes: SimpleChanges) {
		if (changes['reservaSelecionadaPopUp'] && changes['reservaSelecionadaPopUp'].currentValue && !changes['reservaSelecionadaPopUp'].firstChange) {
			this.reservaSelecionadaPopUp = changes['reservaSelecionadaPopUp'].currentValue
			if (this.reservaSelectBox.readOnly) {
				this.reservaSelectBox.readOnly = false
			}
			this.reservaSelectBox.value = this.reservas.find(r => r.id === this.reservaSelecionadaPopUp)
			this.reservaSelectBox.readOnly = true
		}
	}

	salvarNota() {
		if (this.calculado) {
			this.cupomService.createCupom(this.reservaSelectBox.selectedItem.id).subscribe(res => {
				if (res) {
					notify('Operação realizada com sucesso', 'success', 3000)
					this.closePopUpCheckOutEvent.emit(false)
				}
			})
		} else {
			notify('Você deve gerar o cupom primeiro', 'warning', 3000)
		}
	}

	calculaConsumo() {
		if (this.reservaSelectBox.selectedItem === null || this.reservaSelectBox.selectedItem === undefined) {
			notify('É necessário escolher uma reserva', 'error', 3000);
			return
		}
		this.gerarVisible = true
		let id = this.reservaSelectBox.selectedItem.id
		this.consumoService.getConsumo(id).subscribe(res => {
			if (res.status === 200) {
				this.gridData = []
				let consumos: ConsumoModel[] = res.body!

				consumos.forEach(c => {
					// @ts-ignore
					let [ano, mes, dia, hora, minuto, segundo] = c.dataConsumo;
					let data = new Date(ano, mes - 1, dia, hora, minuto, segundo);

					let prod = this.produtos.find(p => p.id === c.produtoId)

					let valorTotal = c.quantidade * prod?.preco!
					this.gridData.push({produto: prod!, quantidade: c.quantidade, dataConsumo: data, valor: valorTotal})
					this.totalDaNota += valorTotal
				})
				notify('Calculo realizado com sucesso!', 'success', 3000)
			}
		})

		let reservaSelecionada: ReservaModel = this.reservaSelectBox.selectedItem

		const dataInicial = new Date(reservaSelecionada.dataEntrada);
		const dataFinal = new Date(reservaSelecionada.dataSaida);

		this.datasDaReserva = []

		this.geraDiasDiarias(dataInicial, dataFinal).forEach(d => {
			this.datasDaReserva.push({data: d, valor: reservaSelecionada.quarto.valor})
			this.totalDaNota += reservaSelecionada.quarto.valor
		});
		this.calculado = true
	}

	geraDiasDiarias(dataInicial: Date, dataFinal: Date): Date[] {
		const days: Date[] = [];
		let currentDate = dataInicial;
		let hoje = new Date();
		if (dataFinal > hoje) {
			dataFinal = hoje
		}

		while (currentDate <= dataFinal) {
			days.push(new Date(currentDate));
			currentDate = new Date(currentDate.getTime() + 86400000); // adiciona 24 horas (em milissegundos)
		}

		return days;
	}


	limparConsumoGerado() {
		this.gerarVisible = false
		this.gridData = []
		this.datasDaReserva = []
		this.totalDaNota = 0
	}

	aplicaDesconto() {
		this.totalDaNotaComDesconto = 0
		this.temDesconto = false
		if (this.tipoDesconto.value === null) {
			notify('Selecione o tipo de desconto', 'error', 3000)
			return;
		}

		if (this.tipoDesconto.value === 'Valor') {
			let desc = Number.parseFloat(this.valorDesconto = this.valorDesconto.replace('.', ','))
			if (desc > this.totalDaNota) {
				notify('O desconto não pode ser maior que o total', 'error', 3000)
				return;
			}
			this.totalDaNotaComDesconto = this.totalDaNota - desc
			this.temDesconto = true
			return;
		}

		if (this.tipoDesconto.value === 'Percentual') {
			let desc = Number.parseFloat(this.valorDesconto.replace(',', '.'))
			if (desc > 100) {
				notify('O desconto não pode ser maior que 100%', 'error', 3000)
				return;
			}
			this.totalDaNotaComDesconto = this.totalDaNota * (1 - (desc / 100))
			this.temDesconto = true
			return;
		}
	}
}


@NgModule({
	imports: [
		DxLoadPanelModule,
		ToolbarModule,
		DxSelectBoxModule,
		DxButtonModule,
		DxDataGridModule,
		CurrencyPipe,
		NgIf,
		DxTextBoxModule,
		DxRadioGroupModule
	],
	declarations: [NotaFormComponent],
	exports: [NotaFormComponent]
})
export class NotaFormModule {
}
