import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ReservaModel} from "../models/reserva.model";
import _ from "lodash";

@Injectable({
	providedIn: 'root'
})
export class ReservaService {

	url: string = 'http://localhost:8080/reservas'

	constructor(private http: HttpClient) {
	}

	getReservas() {
		return this.http.get<ReservaModel[]>(this.url);
	}

	getReservasHoje() {
		return this.http.get<ReservaModel[]>(`${this.url}/hoje`);
	}

	getReserva(id: number) {
		return this.http.get<ReservaModel>(`${this.url}/${id}`, {observe: 'response'});
	}


	createReserva(reserva: ReservaModel) {
		return this.http.post<ReservaModel>(this.url, reserva, {observe: 'response'});
	}

	updateReserva(reserva: ReservaModel) {
		return this.http.put(`${this.url}/${reserva.id}`, reserva, {observe: 'response'});
	}

	deleteReserva(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}

	fazerCheckIn(reserva: ReservaModel) {
		reserva.dataSaida = new Date(reserva.dataSaida)

		let url = `${this.url}/${reserva.id}/checkin`;
		if (!_.isEmpty(reserva.observacao)) {
			url += `?observacao=${encodeURIComponent(reserva.observacao)}`;
		}
		if (_.isDate(reserva.dataSaida)) {
			if (url.includes('?')) {
				url += `&dataSaida=${encodeURIComponent(reserva.dataSaida.toISOString())}`;
			} else {
				url += `?dataSaida=${encodeURIComponent(reserva.dataSaida.toISOString())}`;
			}
		}

		return this.http.post(url, null, {observe: 'response'});
	}
}
