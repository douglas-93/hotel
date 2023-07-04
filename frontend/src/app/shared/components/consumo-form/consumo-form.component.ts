import {Component, EventEmitter, Input, NgModule, Output, ViewChild} from '@angular/core';
import {
	DxButtonModule,
	DxDataGridModule,
	DxLoadPanelModule, DxNumberBoxComponent,
	DxNumberBoxModule,
	DxSelectBoxComponent,
	DxSelectBoxModule,
	DxTextBoxModule
} from "devextreme-angular";
import {ToolbarComponent, ToolbarModule} from "../toolbar/toolbar.component";
import {ReservaModel} from "../../models/reserva.model";
import {ReservaService} from "../../services/reserva.service";
import {ProdutoModel} from "../../models/produto.model";
import {ProdutoService} from "../../services/produto.service";
import {forkJoin, Observable} from "rxjs";
import _ from "lodash";
import notify from "devextreme/ui/notify";
import {CurrencyPipe} from "@angular/common";
import {ConsumoModel} from "../../models/consumo.model";
import {ConsumoService} from "../../services/consumo.service";
import {HttpResponse} from "@angular/common/http";

@Component({
	selector: 'app-consumo-form',
	templateUrl: './consumo-form.component.html',
	styleUrls: ['./consumo-form.component.scss']
})
export class ConsumoFormComponent {

	@ViewChild('reserva', {static: false}) reserva: DxSelectBoxComponent;
	@ViewChild('produto', {static: false}) produto: DxSelectBoxComponent;
	@ViewChild('quantidade', {static: false}) quantidade: DxNumberBoxComponent;
	@ViewChild('toolBar', {static: false}) toolBar: ToolbarComponent;

	@Input() reservaSelecionadaPopUp: number;
	@Output() closeConsumoPopUp = new EventEmitter();

	loadingVisible: boolean = false;
	reservas: ReservaModel[];
	produtos: ProdutoModel[];
	gridData: { produto: ProdutoModel, quantidade: number }[] = [];
	gridDataSelected: { produto: ProdutoModel, quantidade: number } | undefined;


	constructor(private reservaService: ReservaService,
				private produtoService: ProdutoService,
				private consumoService: ConsumoService) {
	}

	ngOnInit(): void {
		this.loadingVisible = true
		forkJoin([this.reservaService.getReservasHoje(), this.produtoService.getAll()])
			.subscribe(([resR, resP]) => {
				this.reservas = resR
				this.produtos = resP.body!.filter(p => p.quantidadeEstoque > 0)
				this.loadingVisible = false

				if (this.reservaSelecionadaPopUp) {
					let r: ReservaModel = this.reservas.filter(e => e.id === this.reservaSelecionadaPopUp)[0]
					this.reserva.instance.option('value', r)
					this.reserva.readOnly = true
					this.toolBar.disableCloseButton = true
				}
			});
	}

	selecionaProduto(e: any) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(p => {
			if (p) {
				this.gridDataSelected = p;
			}
		});
	}

	adicionaAoGrid() {
		let p = this.produto.selectedItem;
		let q = this.quantidade.value;

		if (this.gridData.some(e => e.produto.id === p.id)) {
			notify('Produto já adicionado', 'warning', 3000);
			return;
		}

		if (_.isUndefined(p) || q <= 0) {
			notify('Produto e quantidade são obrigatórios', 'error', 3000);
			return;
		}

		if (p.quantidadeEstoque < q) {
			notify('Você não tem essa quantidade em estoque', 'error', 3000);
			return;
		}

		this.gridData.push({produto: p, quantidade: q});
		this.produto.instance.resetOption('value');
		this.quantidade.value = 0;
	}

	removeDoGrid() {
		if (this.gridDataSelected == undefined) {
			notify('Selecione um produto primeiro', 'warning', 3000);
			return;
		}

		let index = this.gridData.indexOf(this.gridDataSelected);
		this.gridData.splice(index, 1);
		this.gridDataSelected = undefined;
	}

	salvarConsumo() {
		if (this.reserva.selectedItem === null) {
			notify('Selecione uma reserva', 'error', 3000);
			return;
		}

		if (this.gridData.length === 0) {
			notify('É necessário adicionar pelo menos um produto para prosseguir', 'error', 3000);
			return;
		}

		const consumos: ConsumoModel[] = []
		_.forEach(this.gridData, e => {
			let c = new ConsumoModel();
			c.reservaId = this.reserva.selectedItem.id
			c.produtoId = e.produto.id!;
			c.quantidade = e.quantidade;
			c.dataConsumo = new Date();
			consumos.push(c)
		})
		let chamadas: Observable<HttpResponse<ConsumoModel>>[] = consumos.map(e => this.consumoService.createConsumo(e));
		forkJoin(chamadas).subscribe(resp => {
			let statusResp = resp.filter(r => r.status != 201);
			if (statusResp.length === 0) {
				this.loadingVisible = false
				notify('Registro realizado com sucesso', 'success', 3000)
				if (this.reservaSelecionadaPopUp) {
					this.closeConsumoPopUp.emit(false);
				} else {
					this.voltar()
				}
			}
		})

	}

	voltar() {
		window.history.back()
	}
}

@NgModule({
	imports: [
		DxLoadPanelModule,
		ToolbarModule,
		DxSelectBoxModule,
		DxTextBoxModule,
		DxButtonModule,
		DxDataGridModule,
		DxNumberBoxModule,
		CurrencyPipe
	],
	declarations: [ConsumoFormComponent],
	exports: [ConsumoFormComponent]
})
export class ConsumoFormModule {
}
