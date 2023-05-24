import {Component, OnInit} from '@angular/core';
import {HotelModel} from "../../shared/models/hotel.model";
import {HotelService} from "../../shared/services/hotel.service";
import notify from "devextreme/ui/notify";
import {EstadoModel} from "../../shared/models/estado.model";
import {EstadoService} from "../../shared/services/estado.service";
import {CidadeService} from "../../shared/services/cidade.service";


@Component({
	selector: 'app-hotel',
	templateUrl: './hotel.component.html',
	styleUrls: ['./hotel.component.scss']
})
export class HotelComponent implements OnInit {

	hotel: HotelModel = new HotelModel();
	estados: EstadoModel[];
	cidades: string[];
	idEstado: number;
	iscarregando: boolean = false;

	constructor(private hotelService: HotelService,
				private estadoService: EstadoService,
				private cidadeService: CidadeService) {
		this.iscarregando = true
		hotelService.getLastHotelVersion().subscribe(
			data => {
				if (data.status === 200) {
					this.hotel = data.body!
				} else if (data.status === 204) {
					this.mostraMensagem('info', 'Favor fazer o cadastro inicial')
				}
				this.iscarregando = false
			}
		)
		this.carregaEstados()
	}

	ngOnInit(): void {
	}

	carregaEstados() {
		this.iscarregando = true
		this.estadoService.getEstados().subscribe(
			data => {
				this.estados = data.map(e => {
					let estado: EstadoModel = new EstadoModel()
					estado.ufId = e['UF-id']
					estado.ufSigla = e['UF-sigla']
					return estado
				}).sort(this.ordenaSiglas)
				this.iscarregando = false
			}
		)
	}

	ordenaSiglas(a, b) {
		const siglaA = a.ufSigla.toUpperCase();
		const siglaB = b.ufSigla.toUpperCase();

		if (siglaA < siglaB) {
			return -1; // Retorna um número negativo se A for menor que B
		}
		if (siglaA > siglaB) {
			return 1; // Retorna um número positivo se A for maior que B
		}
		return 0; // Retorna 0 se forem iguais
	}

	carregaCidades() {
		if (this.idEstado) {
			this.iscarregando = true
			this.cidadeService.getCidades(this.idEstado).subscribe(
				data => {
					let listaCidades: string[];
					listaCidades = data.map(e => e['distrito-nome']);
					this.cidades = listaCidades;
					this.iscarregando = false;
				}
			)
		}
	}

	salvar() {
		this.iscarregando = true
		this.hotelService.createHotel(this.hotel).subscribe(
			resp => {
				if (resp.status === 201) {
					this.iscarregando = false
					this.mostraMensagem('success', 'Registro salvo com sucesso!')
					setTimeout(this.voltar, 1000)
				}
			}
		)
	}

	voltar() {
		window.history.back();
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

	defineIdEstado(e: any) {
		let estado: EstadoModel = e.selectedItem
		this.idEstado = estado.ufId
		this.carregaCidades()
		this.hotel.cidade = ''
	}
}