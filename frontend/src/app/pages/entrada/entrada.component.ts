import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {EntradaService} from "../../shared/services/entrada.service";
import {EntradaModel} from "../../shared/models/entrada.model";
import {ProdutoModel} from "../../shared/models/produto.model";

@Component({
  selector: 'app-entrada',
  templateUrl: './entrada.component.html',
  styleUrls: ['./entrada.component.scss']
})
export class EntradaComponent implements OnInit {
	entradas: EntradaModel[];
	loadingVisible: boolean;
	entradaSelecionada: ProdutoModel;

  constructor(private router: Router,
			  private entradaService: EntradaService) { }

  ngOnInit(): void {

  }

	editar(e: any) {
		this.router.navigate(['pages', 'entradas', 'edit', this.entradaSelecionada.id])
	}

	selecionaEntrada(e: any) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(entrada => {
			if (entrada) {
				this.entradaSelecionada = entrada;
			}
		});
	}

	buscaEntradas() {
		this.entradaService.getEntradas().subscribe(resp => {
			this.entradas = resp
		})
	}

	novo() {
		this.router.navigate(['pages', 'entradas', 'cad'])
	}

	protected readonly Date = Date;
}
