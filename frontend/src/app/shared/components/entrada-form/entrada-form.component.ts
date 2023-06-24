import {Component, NgModule, ViewChild} from '@angular/core';
import {
	DxButtonModule,
	DxDataGridModule,
	DxDateBoxComponent,
	DxDateBoxModule,
	DxLoadPanelModule,
	DxNumberBoxComponent,
	DxNumberBoxModule,
	DxSelectBoxComponent,
	DxSelectBoxModule,
	DxTextBoxComponent,
	DxTextBoxModule
} from "devextreme-angular";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {ProdutoModel} from "../../models/produto.model";
import {EntradaService} from "../../services/entrada.service";
import {ProdutoService} from "../../services/produto.service";
import notify from "devextreme/ui/notify";
import _ from "lodash";
import {EntradaModel} from "../../models/entrada.model";
import {DatePipe} from "@angular/common";
import {forkJoin, Observable} from "rxjs";
import {HttpResponse} from "@angular/common/http";

@Component({
	selector: 'app-entrada-form',
	templateUrl: './entrada-form.component.html',
	styleUrls: ['./entrada-form.component.scss']
})
export class EntradaFormComponent {

	@ViewChild('produtoBox') produtoBox: DxSelectBoxComponent;
	@ViewChild('dataEntradaBox') dataEntradaBox: DxDateBoxComponent;
	@ViewChild('notaBox') notaBox: DxTextBoxComponent;
	@ViewChild('quantidadeBox') quantidadeBox: DxNumberBoxComponent;
	loadingVisible: boolean = false;
	produtos: ProdutoModel[];
	produtoSelecionado: ProdutoModel | null;
	entradaSelecionada: EntradaModel;
	produtosDataGrid: EntradaModel[];
	hoje: Date = new Date();
	dataEntrada: Date;
	nota: string;

	constructor(private entradaService: EntradaService,
				private produtoService: ProdutoService) {
	}

	ngOnInit() {
		this.loadingVisible = true
		this.produtoService.getAll().subscribe(resp => {
			if (resp.status === 200) {
				this.produtos = resp.body!
				this.loadingVisible = false
			}
		});
		this.produtosDataGrid = []
		this.produtoSelecionado = new ProdutoModel();
	}

	salvar() {
		let nota = this.notaBox.value
		if (_.isEmpty(nota) || _.isNull(nota) || _.isUndefined(nota)) {
			notify('A nota precisa ser informada', 'error')
			return
		}
		if (this.produtosDataGrid.length === 0) {
			notify('Deve existir pelo menos um produto', 'error')
			return
		}

		this.loadingVisible = true

		this.dataEntrada = this.dataEntrada === undefined ? this.hoje : this.dataEntrada

		let chamadas: Observable<HttpResponse<EntradaModel>>[] = _.forEach(this.produtosDataGrid, e => {
			e.nota = nota;
			e.dataEntrada = this.dataEntrada;
		}).map(e => this.entradaService.createEntrada(e))

		forkJoin(chamadas).subscribe(resp => {
			let statusResp = resp.filter(r => r.status != 201);
			if (statusResp.length === 0) {
				this.loadingVisible = false
				notify('Entrada realizada com sucesso', 'success')
				this.voltar()
			}
		})
	}

	deletaEntrada() {

	}

	defineDataEntrada(e: any) {
		this.dataEntrada = <Date>e;
	}

	adicionaProduto() {
		let p: ProdutoModel = this.produtoBox.instance.option().selectedItem
		if (p === null) {
			notify('Selecione um produto', 'error')
			return
		}
		let q: number = this.quantidadeBox.instance.option().value!
		if (q <= 0) {
			notify('A quantidade não pode ser zero', 'error')
			return
		}
		let e: EntradaModel = new EntradaModel();
		e.produto = p
		e.quantidade = q

		let jaExsite = _.filter(this.produtosDataGrid, e => e.produto == p)

		if (jaExsite.length === 0) {
			this.produtosDataGrid.push(e)
		} else {
			notify('O produto já está na lista', 'warning')
		}
		this.produtoBox.instance.resetOption('value')
		this.quantidadeBox.instance.resetOption('value')
	}

	voltar() {
		window.history.back()
	}

	removeProduto() {
		let e = _.filter(this.produtosDataGrid, e => e.produto === this.entradaSelecionada.produto)
		let index = this.produtosDataGrid.indexOf(e[0]);
		if (index != -1) {
			this.produtosDataGrid.splice(index, 1);
		}
	}

	selecionaEntrada(e: any) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(entrada => {
			if (entrada) {
				this.entradaSelecionada = entrada;
			}
		});
	}
}

@NgModule({
	imports: [
		DxTextBoxModule,
		DxSelectBoxModule,
		DxNumberBoxModule,
		DxDateBoxModule,
		ToolbarModule,
		DxLoadPanelModule,
		DxDataGridModule,
		DxButtonModule,
		DatePipe
	],
	declarations: [EntradaFormComponent],
	exports: [EntradaFormComponent]
})
export class EntradaFormModule {
}
