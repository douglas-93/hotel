import {Component, OnInit} from '@angular/core';
import {QuartoService} from "../../shared/services/quarto.service";
import {QuartoModel} from "../../shared/models/quarto.model";
import {Router} from "@angular/router";

@Component({
	selector: 'app-quartos',
	templateUrl: './quartos.component.html',
	styleUrls: ['./quartos.component.scss']
})
export class QuartosComponent implements OnInit {

	isReadOnly: boolean = true;
	quartos: QuartoModel[];
	quartoSelecionado: QuartoModel;
	loadingVisible: boolean = false;

	constructor(private quartoService: QuartoService,
				private router: Router) {
	}

	ngOnInit(): void {
	}

	buscaQuartos() {
		this.loadingVisible = true;
		this.quartoService.getQuartos().subscribe(resp => {
			if (resp.status === 200) {
				this.quartos = resp.body!
				this.loadingVisible = false;
			}
		})
	}

	selecionaQuarto(e) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(quarto => {
			if (quarto) {
				this.quartoSelecionado = quarto;
			}
		});
	}

	novo() {
		this.router.navigate(['pages', 'quartos', 'cad'])
	}

	editar(e: MouseEvent) {
		this.router.navigate(['pages', 'quartos', 'edit', this.quartoSelecionado.id])
	}
}
