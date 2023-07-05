import {Component, NgModule, ViewChild} from '@angular/core';
import {
	DxButtonModule,
	DxDataGridModule,
	DxLoadPanelModule,
	DxSelectBoxComponent,
	DxSelectBoxModule
} from "devextreme-angular";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {CurrencyPipe} from "@angular/common";
import {ProdutoModel} from "../../models/produto.model";
import {forkJoin} from "rxjs";
import {ReservaModel} from "../../models/reserva.model";
import {ReservaService} from "../../services/reserva.service";
import {ProdutoService} from "../../services/produto.service";
import {ConsumoService} from "../../services/consumo.service";
import {ConsumoModel} from "../../models/consumo.model";
import notify from "devextreme/ui/notify";

@Component({
	selector: 'app-nota-form',
	templateUrl: './nota-form.component.html',
	styleUrls: ['./nota-form.component.scss']
})
export class NotaFormComponent {

	@ViewChild('reserva') reservaSelectBox: DxSelectBoxComponent;

	loadingVisible: boolean = false;
	gridData: { produto: ProdutoModel, quantidade: number, dataConsumo: Date }[];
	gridDataSelected: { produto: ProdutoModel, quantidade: number, dataConsumo: Date } | undefined;
	reservas: ReservaModel[];
	produtos: ProdutoModel[];


	constructor(private reservaService: ReservaService,
				private produtoService: ProdutoService,
				private consumoService: ConsumoService) {
	}

	ngOnInit(): void {
		this.loadingVisible = true
		forkJoin([this.reservaService.getReservasHoje(), this.produtoService.getAll()])
			.subscribe(([resR, resP]) => {
				this.reservas = resR
				this.produtos = resP.body!
				this.loadingVisible = false

				/*if (this.reservaSelecionadaPopUp) {
					let r: ReservaModel = this.reservas.filter(e => e.id === this.reservaSelecionadaPopUp)[0]
					this.reserva.instance.option('value', r)
					this.reserva.readOnly = true
					this.toolBar.disableCloseButton = true
				}*/
			});
	}

	selecionaProduto(e: any) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(p => {
			if (p) {
				this.gridDataSelected = p;
			}
		});
	}

	salvarNota() {

	}

	calculaConsumo() {
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

					this.gridData.push({produto: prod!, quantidade: c.quantidade, dataConsumo: data})
				})
				notify('Calculo realizado com sucesso!', 'success', 3000)
			}
		})
	}

}


@NgModule({
	imports: [
		DxLoadPanelModule,
		ToolbarModule,
		DxSelectBoxModule,
		DxButtonModule,
		DxDataGridModule,
		CurrencyPipe
	],
	declarations: [NotaFormComponent],
	exports: [NotaFormComponent]
})
export class NotaFormModule {
}
