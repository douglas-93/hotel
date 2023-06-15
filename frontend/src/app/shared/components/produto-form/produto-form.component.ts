import {Component, NgModule} from '@angular/core';
import {ToolbarModule} from "../toolbar/toolbar.component";
import {
	DxButtonModule,
	DxCheckBoxModule,
	DxFileUploaderModule, DxListModule,
	DxNumberBoxModule,
	DxSelectBoxModule,
	DxTextBoxModule
} from "devextreme-angular";
import {NgIf} from "@angular/common";
import {QuartoFormComponent} from "../quarto-form/quarto-form.component";

@Component({
  selector: 'app-produto-form',
  templateUrl: './produto-form.component.html',
  styleUrls: ['./produto-form.component.scss']
})
export class ProdutoFormComponent {

}

@NgModule({
	imports: [],
	declarations: [ProdutoFormComponent],
	exports: [ProdutoFormComponent]
})
export class ProdutoFormModule {
}
