import {Component, NgModule} from '@angular/core';
import {ProdutoFormComponent} from "../produto-form/produto-form.component";

@Component({
  selector: 'app-nota-form',
  templateUrl: './nota-form.component.html',
  styleUrls: ['./nota-form.component.scss']
})
export class NotaFormComponent {

}

@NgModule({
	imports: [],
	declarations: [NotaFormComponent],
	exports: [NotaFormComponent]
})
export class NotaFormModule {
}
