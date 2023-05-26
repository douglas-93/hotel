import {Component, NgModule} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";

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
