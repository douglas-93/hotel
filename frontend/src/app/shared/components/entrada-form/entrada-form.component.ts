import {Component, NgModule} from '@angular/core';
import {NotaFormComponent} from "../nota-form/nota-form.component";

@Component({
  selector: 'app-entrada-form',
  templateUrl: './entrada-form.component.html',
  styleUrls: ['./entrada-form.component.scss']
})
export class EntradaFormComponent {

}

@NgModule({
	imports: [],
	declarations: [EntradaFormComponent],
	exports: [EntradaFormComponent]
})
export class EntradaFormModule {
}
