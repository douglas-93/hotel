import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
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
	getReserva(id: number){
		return this.http.get<ReservaModel>(`${this.url}/${id}`, {observe: 'response'});
	}


	createReserva(reserva: ReservaModel){
		return this.http.post<ReservaModel>(this.url, reserva, {observe: 'response'});
	}

	updateReserva(reserva: ReservaModel){
		return this.http.put(`${this.url}/${reserva.id}`, reserva, {observe: 'response'});
	}

	deleteReserva(id: number){
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}

	fazerCheckIn(reserva: ReservaModel){
		const formData = new FormData();
		if (!_.isEmpty(reserva.observacao)) {
			formData.append('observacao', reserva.observacao); // Verifica se a observacao está definida
		}
		if (!_.isEmpty(reserva.dataSaida)) {
            formData.append('dataSaida', reserva.dataSaida!.toISOString()); // Converte a data para string ISO formatada ou utiliza uma string vazia
        }

		const headers = new HttpHeaders().set('Content-Type', 'multipart/form-data'); // Altera o tipo de conteúdo para 'multipart/form-data'

		return this.http.post(`${this.url}/${reserva.id}/checkin`, formData, { headers, observe: 'response' });

		// const formData = new FormData();
		// formData.append('observacao', reserva.observacao);
		// formData.append('dataSaida', reserva.dataSaida.toString());
		//
		// const headers = new HttpHeaders().set('Content-Type', 'application/json')
		//
		// return this.http.post(`${this.url}/${reserva.id}/checkin`, formData, {headers, observe: 'response'});
	}
}
