import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.scss']
})
export class EntradaComponent implements OnInit {
	entradas: any;
	loadingVisible: boolean;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

	editar(e: any) {

	}

	selecionaEntrada(e: any) {

	}

	buscaEntradas() {

	}

	novo() {
		this.router.navigate(['pages', 'entradas', 'cad'])
	}
}
