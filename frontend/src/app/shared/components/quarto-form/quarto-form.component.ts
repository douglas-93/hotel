import {Component, NgModule} from '@angular/core';
import {QuartoService} from "../../services/quarto.service";
import {Router} from "@angular/router";
import {QuartoModel} from "../../models/quarto.model";
import notify from "devextreme/ui/notify";
import {custom} from "devextreme/ui/dialog";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {DxCheckBoxModule, DxSelectBoxModule, DxTextBoxModule} from "devextreme-angular";
import {TiposQuartoEnum} from "../../enums/tipos-quartos.enum";
import {CategoriasEnum} from "../../enums/categorias.enum";

@Component({
	selector: 'app-quarto-form',
	templateUrl: './quarto-form.component.html',
	styleUrls: ['./quarto-form.component.scss']
})
export class QuartoFormComponent {

	isUpdate: boolean;
	quarto: QuartoModel = new QuartoModel();
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

	constructor(private quartoService: QuartoService,
				private router: Router) {
		let id = router.url.split('/').pop()!
		if (id.match(/[0-9]+/)) {
			this.isUpdate = true
			this.quartoService.getQuarto(Number.parseInt(id)).subscribe(
				resp => {
					if (resp.status === 200) {
						this.quarto = resp.body!
					}
				}
			)
		}
	}

	salvar() {
		if (this.isUpdate) {
			this.atualizaQuarto()
		} else {
			this.criarQuarto()
		}
	}

	criarQuarto() {
		this.quartoService.createQuarto(this.quarto).subscribe(
			resp => {
				if (resp.status === 201) {
					this.mostraMensagem('success', 'Quarto salvo com sucesso.')
					setTimeout(this.voltar, 1000)
				}
			}
		)
	}

	atualizaQuarto() {
		this.quartoService.updateQuarto(this.quarto).subscribe(
			resp => {
				if (resp.status === 200) {
					this.mostraMensagem('success', 'Alteração salva com sucesso.')
					setTimeout(this.voltar, 1000)
				}
			}
		)
	}

	deletaQuarto() {
		let myDialog = custom({
			title: "Confirmação",
			messageHtml: `Tem certeza que deseja excluir o quarto ${this.quarto.nome}?<br>Essa ação é <b>irreversível</b>!`,
			buttons: [{
				text: "Excluir",
				onClick: () => {
					this.quartoService.deleteQuarto(this.quarto.id!).subscribe(
						resp => {
							if (resp.status === 204) {
								this.mostraMensagem('success', 'Quarto deletado com sucesso.')
								setTimeout(this.voltar, 1000)
							}
						}
					)
				}
			},
				{
					text: "Cancelar",
					onClick: (e) => {
						myDialog.hide()
					}
				},
				// ...
			]
		});
		myDialog.show()

	}

	mostraMensagem(tipo: string, mensagem: string) {
		notify({
				message: `${mensagem}`,
				icon: 'clear',
				type: tipo,
				displayTime: 3500,
				animation: {
					show: {
						type: 'fade', duration: 400, from: 0, to: 1,
					},
					hide: {type: 'fade', duration: 40, to: 0},
				},
			},
			{position: 'bottom center', direction: "up-push"});
	}

	voltar() {
		window.history.back()
	}
}

@NgModule({
	imports: [
		ToolbarModule,
		DxCheckBoxModule,
		DxTextBoxModule,
		DxSelectBoxModule
	],
	declarations: [QuartoFormComponent],
	exports: [QuartoFormComponent]
})
export class QuartoFormModule {
}
