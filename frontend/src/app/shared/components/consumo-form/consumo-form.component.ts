import {Component, NgModule} from '@angular/core';
import {EntradaFormComponent} from "../entrada-form/entrada-form.component";

@Component({
  selector: 'app-consumo-form',
  templateUrl: './consumo-form.component.html',
  styleUrls: ['./consumo-form.component.scss']
})
export class ConsumoFormComponent {

}

@NgModule({
	imports: [],
	declarations: [ConsumoFormComponent],
	exports: [ConsumoFormComponent]
})
export class ConsumoFormModule {
}
