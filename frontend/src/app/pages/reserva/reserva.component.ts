import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {ReservaService} from "../../shared/services/reserva.service";
import {ReservaModel} from "../../shared/models/reserva.model";

@Component({
	selector: 'app-reserva',
	templateUrl: './reserva.component.html',
	styleUrls: ['./reserva.component.scss']
})
export class ReservaComponent implements OnInit {

	reservas: ReservaModel[];
	reservaSelecionada: ReservaModel;
	loadingVisible: boolean = false;

	constructor(private router: Router,
				private reservaService: ReservaService) {
	}

	ngOnInit(): void {
	}

	novo() {
		this.router.navigate(['pages', 'reservas', 'cad'])
	}

	buscarReservas() {
		this.loadingVisible = true;
		this.reservaService.getReservas().subscribe(resp => {
			this.reservas = resp
			this.loadingVisible = false;
		})
	}

	selecionaReserva(e) {
		e.component.byKey(e.currentSelectedRowKeys[0]).done(reserva => {
			if (reserva) {
				this.reservaSelecionada = reserva;
			}
		});
	}

	editar(e: MouseEvent) {
		this.router.navigate(['pages', 'reservas', 'cad', this.reservaSelecionada.id])
	}
}
