import {Component, EventEmitter, Input, NgModule, Output} from '@angular/core';
import {DxButtonModule, DxMenuModule} from "devextreme-angular";
import {NgIf} from "@angular/common";

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss']
})
export class ToolbarComponent {
	@Output() filtrar = new EventEmitter();
	@Output() novoCad = new EventEmitter();
	@Output() salvar = new EventEmitter();

	@Input()
	cad: boolean;

	filtrarEnv(e: any) {
		this.filtrar.emit(e)
	}
	novoCadEnv(e: any) {
		this.novoCad.emit(e)
	}
	salvaEnv(e: any) {
		this.salvar.emit(e)
	}
	voltar() {
		window.history.back()
	}
}

@NgModule({
	imports: [
		DxMenuModule,
		NgIf,
		DxButtonModule
	],
	declarations: [ToolbarComponent],
	exports: [ToolbarComponent],
})
export class ToolbarModule{
}
