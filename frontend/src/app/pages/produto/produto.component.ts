import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
	selector: 'app-produto',
	templateUrl: './produto.component.html',
	styleUrls: ['./produto.component.scss']
})
export class ProdutoComponent implements OnInit {
	loadingVisible: boolean;
	produtos: any;

	constructor(private router: Router) {
	}

	ngOnInit(): void {
	}

	buscaProdutos() {

	}

	novo() {
		this.router.navigate(['pages', 'produtos', 'cad'])
	}

	editar(e: any) {

	}

	selecionaProduto(e: any) {

	}
}
