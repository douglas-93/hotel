import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ProdutoService} from "../../shared/services/produto.service";
import {ProdutoModel} from "../../shared/models/produto.model";

@Component({
	selector: 'app-produto',
	templateUrl: './produto.component.html',
	styleUrls: ['./produto.component.scss']
})
export class ProdutoComponent implements OnInit {
	loadingVisible: boolean;
	produtos: ProdutoModel[];
	produtoSelecionado: ProdutoModel;

	constructor(private router: Router,
				private prodService: ProdutoService) {
	}

	ngOnInit(): void {
	}

	buscaProdutos() {
		this.prodService.getAll().subscribe(resp => {
			if (resp.status === 200) {
				this.produtos = resp.body!
			}
		})
	}

	novo() {
		this.router.navigate(['pages', 'produtos', 'cad'])
	}

	editar(e: any) {
		this.router.navigate(['pages', 'produtos', 'edit', this.produtoSelecionado.id])
	}

	selecionaProduto(e: any) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(prod => {
			if (prod) {
				this.produtoSelecionado = prod;
			}
		});
	}
}
