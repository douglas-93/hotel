import {Component, NgModule, ViewChild} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {HospedeModel} from "../../models/hospede.model";
import {DxDateBoxModule, DxNumberBoxModule, DxTextAreaModule, DxTextBoxModule} from "devextreme-angular";
import notify from "devextreme/ui/notify";
import {HospedeService} from "../../services/hospede.service";
import {ReactiveFormsModule} from "@angular/forms";
import {Router} from "@angular/router";
import {custom} from "devextreme/ui/dialog";

@Component({
	selector: 'app-hospede-form',
	templateUrl: './hospede-form.component.html',
	styleUrls: ['./hospede-form.component.scss']
})
export class HospedeFormComponent {

	@ViewChild('data')
	data;
	hospede: HospedeModel = new HospedeModel();
	isUpdate: boolean = false;

	// tipoMensagem = ['error', 'success', 'info', 'warning']

	constructor(private hospService: HospedeService,
				private router: Router) {
		let id = router.url.split('/').pop()!
		if (id.match(/[0-9]+/)) {
			this.isUpdate = true
			this.hospService.getHospede(Number.parseInt(id)).subscribe(
				resp => {
					this.hospede = resp.body!
					this.data.value = new Date(this.hospede.nascimento)
				}
			)
		}
	}

	salvar() {
		if (this.isUpdate) {
			this.atualizaHospede()
		} else {
			this.criarHospede()
		}
	}

	criarHospede() {
		if (this.verificaDados()) {
			this.hospede.nascimento = this.data.value
			this.hospService.createHospede(this.hospede).subscribe(
				resp => {
					if (resp.status === 201) {
						this.mostraMensagem('success', 'Hospede salvo com sucesso.')
						setTimeout(this.voltar, 1000)
					}
				}
			)
		}
	}

	atualizaHospede() {
		if (this.verificaDados()) {
			this.hospede.nascimento = this.data.value
			this.hospService.updateHospede(this.hospede).subscribe(
				resp => {
					if (resp.status === 200) {
						this.mostraMensagem('success', 'Atualização realizada com sucesso.')
						setTimeout(this.voltar, 1000)
					}
				}
			)
		}
	}

	deletaHospede() {
		let myDialog = custom({
			title: "Confirmação",
			messageHtml: `Tem certeza que deseja excluir o hospede ${this.hospede.nome}?<br>Essa ação é <b>irreversível</b>!`,
			buttons: [{
				text: "Excluir",
				onClick: () => {
					this.hospService.deleteHospede(this.hospede.id!).subscribe(
						resp => {
							if (resp.status === 204) {
								this.mostraMensagem('success', 'Hospede deletado com sucesso.')
								setTimeout(this.voltar, 1000)
							}
						}
					)
				}
			},
				{
					text: "Cancelar",
					onClick: () => {
						myDialog.hide()
					}
				},
				// ...
			]
		});
		myDialog.show()

	}

	verificaDados() {
		let tipo: string = 'error'
		let erros: number = 0

		if (this.hospede.nome === undefined || this.hospede.nome.length === 0 || !this.hospede.nome) {
			this.mostraMensagem(tipo, 'Favor preencher o Nome.')
			erros++
		}
		if (this.hospede.cpf === undefined || this.hospede.cpf.length < 11 || !this.hospede.cpf) {
			this.mostraMensagem(tipo, 'Favor preencher o CPF.')
			erros++
		}
		if (!this.data.value) {
			this.mostraMensagem(tipo, 'Favor preencher a Data de Nascimento.')
			erros++
		}
		if ((this.hospede.email === undefined || this.hospede.email === null ||
				!this.hospede.email.match(/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/) ||
				!this.hospede.email) &&
			(this.hospede.telefone === undefined || this.hospede.telefone === null ||
				!this.hospede.telefone.match(/(\d{2})(\d{4})(\d{4})/g) ||
				!this.hospede.telefone) &&
			(this.hospede.celular === undefined || this.hospede.celular === null ||
				!this.hospede.celular.match(/(\d{2})(\d{5})(\d{4})/g) ||
				!this.hospede.celular)) {
			this.mostraMensagem(tipo, 'Favor preencher ao menos um tipo de contato.')
			erros++
		}
		return erros === 0
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
	imports: [CommonModule, ToolbarModule, DxTextBoxModule, DxNumberBoxModule, DxDateBoxModule, DxTextAreaModule, ReactiveFormsModule,],
	declarations: [HospedeFormComponent],
	exports: [HospedeFormComponent]
})
export class HospedeFormModule {
}
