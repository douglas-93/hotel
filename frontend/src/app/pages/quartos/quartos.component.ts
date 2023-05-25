import {Component, OnInit} from '@angular/core';
import {QuartoService} from "../../shared/services/quarto.service";
import {QuartoModel} from "../../shared/models/quarto.model";
import {TiposQuartoEnum} from "../../shared/enums/tipos-quartos.enum";
import {CategoriasEnum} from "../../shared/enums/categorias.enum";

@Component({
	selector: 'app-quartos',
	templateUrl: './quartos.component.html',
	styleUrls: ['./quartos.component.scss']
})
export class QuartosComponent implements OnInit {

	isReadOnly: boolean = true;
	quartos: QuartoModel[];
	tiposQuartos = Object.values(TiposQuartoEnum).filter(f => isNaN(Number(f))).map(valor => ({
		value: valor,
		// @ts-ignore
		text: valor.replace('_', ' ')
	}));
	categorias = Object.values(CategoriasEnum).filter(f => isNaN(Number(f))).map(valor => ({
		value: valor,
		// @ts-ignore
		text: valor.replace('_', ' ')
	}));

	constructor(private quartoService: QuartoService) {
	}

	ngOnInit(): void {
	}

	buscaQuartos() {
		this.quartoService.getQuartos().subscribe(resp => {
			if (resp.status === 200) {
				this.quartos = resp.body!
			}
		})
	}
}
