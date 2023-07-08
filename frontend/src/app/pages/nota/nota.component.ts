import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CupomConsumoService} from "../../shared/services/cupomConsumo.service";
import {CupomConsumoModel} from "../../shared/models/cupomConsumo.model";

@Component({
	selector: 'app-nota',
	templateUrl: './nota.component.html',
	styleUrls: ['./nota.component.scss']
})
export class NotaComponent implements OnInit {
	cupons: CupomConsumoModel[];
	loadingVisible: boolean;

	constructor(private router: Router,
				private cupomService: CupomConsumoService) {
	}

	ngOnInit(): void {
		this.cupons = []
	}

	selecionaNota(e: any) {

	}

	editar(e: any) {

	}

	novo() {
		this.router.navigate(['pages', 'notas', 'cad'])
	}

	buscaNotas() {
		this.cupomService.getCupons().subscribe(res => {
			this.cupons = res
		})
	}
}
