import {Component, NgModule} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";
import {DxCheckBoxModule, DxSelectBoxModule, DxTextBoxModule} from "devextreme-angular";
import {QuartoFormComponent} from "../quarto-form/quarto-form.component";

@Component({
  selector: 'app-reserva-form',
  templateUrl: './reserva-form.component.html',
  styleUrls: ['./reserva-form.component.scss']
})
export class ReservaFormComponent {

}

@NgModule({
	imports: [
		ToolbarModule
	],
	declarations: [ReservaFormComponent],
	exports: [ReservaFormComponent]
})
export class ReservaFormModule {
}
