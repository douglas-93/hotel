import {Component, NgModule, ViewChild} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";
import {
	DxButtonModule,
	DxDataGridModule,
	DxRadioGroupModule,
	DxTabPanelComponent,
	DxTabPanelModule,
	DxTextBoxComponent,
	DxTextBoxModule
} from "devextreme-angular";
import {ProdutoModel} from "../../models/produto.model";
import _ from "lodash";
import notify from "devextreme/ui/notify";
import {CurrencyPipe} from "@angular/common";
import {ProdutoService} from "../../services/produto.service";
import {Router} from "@angular/router";

@Component({
	selector: 'app-produto-form',
	templateUrl: './produto-form.component.html',
	styleUrls: ['./produto-form.component.scss']
})
export class ProdutoFormComponent {

	@ViewChild('valor', {static: false}) valorUnico: DxTextBoxComponent;
	@ViewChild('valorVarios', {static: false}) valorVarios: DxTextBoxComponent;
	@ViewChild('nomeVarios', {static: false}) nomeVarios: DxTextBoxComponent;
	@ViewChild('tabPanel', {static: false}) painel: DxTabPanelComponent;
	produto: ProdutoModel;
	produtos: ProdutoModel[];
	produtoSelecionadoGrid: ProdutoModel;
	isEditing: boolean = false;


	constructor(private prodService: ProdutoService,
				private router: Router) {

		this.produto = new ProdutoModel();
		this.produtos = [];

		let id = router.url.split('/').pop()
		if (id!.match(/[0-9]+/)) {
			prodService.getById(id).subscribe(resp => {
				if (resp.status === 200) {
					this.produto = resp.body!
					let preco = this.produto.preco.toString()
					if (preco.length <= 3 && !preco.includes('.')) {
						preco += '00'
					} else if (preco.includes('.') && preco.split('.')[1].length == 1) {
						preco += '0'
					}
					this.formataValor(preco, 'U')
					this.isEditing = true
				}
			})
		}
	}

	selecionaProduto(e) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(p => {
			if (p) {
				this.produtoSelecionadoGrid = p;
			}
		});
	}

	formataValor(e: any, box: string) {
		let stringValue = e
		stringValue = stringValue.replace(/[^\d]/g, '')
		let formattedValue = stringValue.replace(/(\d{2})$/, ',$1');

		if (stringValue.length > 2) {
			if (stringValue.length >= 4) {
				formattedValue = formattedValue.replace(/^0/, '')
			}
			formattedValue = formattedValue.replace(/(\d)(?=(\d{3})+(?!\d))/g, '$1.');
		} else {
			formattedValue = `0${formattedValue}`;
		}

		if (formattedValue.match(/^0+,00$/)) {
			formattedValue = ''
		}

		if (box === 'U') {
			this.valorUnico.value = formattedValue
			this.produto.preco = parseFloat(formattedValue.replace('.', '').replace(',', '.'))
		} else {
			this.valorVarios.value = formattedValue
		}
	}

	salvar() {
		if (this.painel.selectedIndex == 0) {
			if (this.produto.nome == '' || this.produto.nome === undefined) {
				this.mostraMensagem('Nome do produto é obrigatório', 'error')
				return;
			}
			if (_.isUndefined(this.produto.preco) && !_.isNumber(this.produto.preco)) {
				this.mostraMensagem('O valor do produto é obrigatório', 'error')
				return
			}
			this.prodService.createProduto(this.produto).subscribe(resp => {
				if (resp.status === 201) {
					this.mostraMensagem('Produto gravado com sucesso', 'success')
				}
				setTimeout(this.voltar)
			})
		}

		if (this.painel.selectedIndex == 1) {
			if (this.produtos.length === 0) {
				this.mostraMensagem('Adicione pelo menos 1 produto antes de prosseguir', 'error')
				return
			}
			this.prodService.createProdutos(this.produtos).subscribe(resp => {
				if (resp.status === 201) {
					this.mostraMensagem('Produtos gravados com sucesso', 'success')
				}
				setTimeout(this.voltar)
			})
		}
	}

	mostraMensagem(mensagem: string, tipo: string, tempo: number = 3000) {
		notify({
				message: `${mensagem}`,
				type: `${tipo}`,
				displayTime: 3000,
				animation: {
					show: {
						type: 'fade', duration: 400, from: 0, to: 1,
					},
					hide: {type: 'fade', duration: 40, to: 0},
				},
			},
			{position: 'bottom center', direction: 'up-push'})
	}

	limparGrid() {
		this.produtos = []
		this.mostraMensagem('Tabela limpa com sucesso', 'info')
	}

	removeProduto() {
		if (this.produtos.length == 0) {
			this.mostraMensagem('Não existem produtos a serem removidos', 'warning')
		}
		let index = this.produtos.indexOf(this.produtoSelecionadoGrid);
		if (index != -1) {
			this.produtos.splice(index, 1);
			this.mostraMensagem('Produto removido com sucesso', 'success', 1000)
		} else {
			this.mostraMensagem('Selecione produto a ser removido da lista', 'warning')
		}
	}

	adicionaAoGrid() {
		if (this.nomeVarios.value == undefined || this.nomeVarios.value == '') {
			this.mostraMensagem('O nome do produto é obrigatório', 'error')
			return
		}
		if (this.valorVarios.value == undefined || this.valorVarios.value == '') {
			this.mostraMensagem('Valor é obrigatório', 'error')
			return
		}
		const prod = new ProdutoModel();
		prod.nome = this.nomeVarios.value
		prod.preco = parseFloat(this.valorVarios.value
			.replace('.', '')
			.replace(',', '.'))
		this.produtos.push(prod)

		this.nomeVarios.value = ''
		this.valorVarios.value = ''
	}

	voltar() {
		window.history.back();
	}

	defineValorUnico() {
		this.produto.preco = parseFloat(this.valorUnico.value
			.replace('.', '')
			.replace(',', '.')
		)
	}

	deletaProduto() {
		this.prodService.deleteProduto(this.produto.id!).subscribe(resp => {
			if (resp.status === 204) {
				this.mostraMensagem('Produto Removido com sucesso', 'success')
				this.voltar()
			}
		});
	}
}

@NgModule({
	imports: [
		ToolbarModule,
		DxRadioGroupModule,
		DxTabPanelModule,
		DxTextBoxModule,
		DxButtonModule,
		DxDataGridModule,
		CurrencyPipe
	],
	declarations: [ProdutoFormComponent],
	exports: [ProdutoFormComponent]
})
export class ProdutoFormModule {
}
