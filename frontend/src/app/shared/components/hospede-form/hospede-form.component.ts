import {Component, NgModule, ViewChild} from '@angular/core';
import {CommonModule} from "@angular/common";
import {ToolbarModule} from "../toolbar/toolbar.component";
import {HospedeModel} from "../../models/hospede.model";
import {DxDateBoxModule, DxNumberBoxModule, DxTextAreaModule, DxTextBoxModule} from "devextreme-angular";
import notify from "devextreme/ui/notify";
import {HospedeService} from "../../services/hospede.service";

@Component({
	selector: 'app-hospede-form',
	templateUrl: './hospede-form.component.html',
	styleUrls: ['./hospede-form.component.scss']
})
export class HospedeFormComponent {

	@ViewChild('data')
	data;
	novoHospede: HospedeModel = new HospedeModel();

	// tipoMensagem = ['error', 'success', 'info', 'warning']

	constructor(private hospService: HospedeService) {
	}

	criarHopede() {
		if (this.verificaDados()) {
			this.novoHospede.nascimento = this.data.value
			this.hospService.createHospede(this.novoHospede).subscribe(
				resp => {
					if (resp.status === 201) {
						this.mostraMensagem('success', 'Hospede salvo com sucesso.')
						setTimeout(this.voltar, 1000)
					}
				}
			)
		}
	}

	verificaDados() {
		let tipo: string = 'error'
		let erros: number = 0

		if (this.novoHospede.nome === undefined || this.novoHospede.nome.length === 0 || !this.novoHospede.nome) {
			this.mostraMensagem(tipo, 'Favor preencher o Nome.')
			erros++
		}
		if (this.novoHospede.cpf === undefined || this.novoHospede.cpf.length < 11 || !this.novoHospede.cpf) {
			this.mostraMensagem(tipo, 'Favor preencher o CPF.')
			erros++
		}
		if (!this.data.value) {
			this.mostraMensagem(tipo, 'Favor preencher a Data de Nascimento.')
			erros++
		}
		if ((this.novoHospede.email === undefined || this.novoHospede.email.length === 0 || !this.novoHospede.email) &&
			(this.novoHospede.telefone === undefined || !this.novoHospede.telefone.match(/(\d{2})(\d{4})(\d{4})/g) || !this.novoHospede.telefone) &&
			(this.novoHospede.celular === undefined || !this.novoHospede.celular.match(/(\d{2})(\d{5})(\d{4})/g) || !this.novoHospede.celular)) {
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
	imports: [CommonModule, ToolbarModule, DxTextBoxModule, DxNumberBoxModule, DxDateBoxModule, DxTextAreaModule,],
	declarations: [HospedeFormComponent],
	exports: [HospedeFormComponent]
})
export class HospedeFormModule {
}
