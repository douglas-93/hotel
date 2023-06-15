import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
	selector: 'app-nota',
	templateUrl: './nota.component.html',
	styleUrls: ['./nota.component.scss']
})
export class NotaComponent implements OnInit {
	notas: any;
	loadingVisible: boolean;

	constructor(private router: Router) {
	}

	ngOnInit(): void {
	}

	selecionaNota(e: any) {

	}

	editar(e: any) {

	}

	novo() {
		this.router.navigate(['pages', 'notas', 'cad'])
	}

	buscaNotas() {

	}
}
