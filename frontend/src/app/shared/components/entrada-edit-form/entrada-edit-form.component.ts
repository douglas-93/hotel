import {Component, NgModule} from '@angular/core';
import {
	DxButtonModule,
	DxDataGridModule,
	DxDateBoxModule,
	DxLoadPanelModule,
	DxNumberBoxModule,
	DxSelectBoxModule,
	DxTextBoxModule
} from "devextreme-angular";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {DatePipe} from "@angular/common";
import {Router} from "@angular/router";
import {EntradaService} from "../../services/entrada.service";
import {EntradaModel} from "../../models/entrada.model";

@Component({
  selector: 'app-entrada-edit-form',
  templateUrl: './entrada-edit-form.component.html',
  styleUrls: ['./entrada-edit-form.component.scss']
})
export class EntradaEditFormComponent {

	entrada: EntradaModel = new EntradaModel();
	data: Date;
	isLoading: boolean = false;

	constructor(private router: Router,
				private entradaService: EntradaService) {
	}

	ngOnInit(): void {
		let id = this.router.url.split('/').pop();
		if (id) {
			this.isLoading = true;
			this.entradaService.getEntrada(Number.parseInt(id)).subscribe(resp => {
				if (resp.status === 200) {
					this.entrada = resp.body!;
					// @ts-ignore
					const [ano, mes, dia, hora, minuto, segundo] = this.entrada.data
					this.data = new Date(ano, mes-1, dia, hora, minuto, segundo);
					this.isLoading = false;
				}
			})
		}
	}

	salvar() {

	}

	deletaEntrada() {

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
	declarations: [EntradaEditFormComponent],
	exports: [EntradaEditFormComponent]
})
export class EntradaEditFormModule {
}
