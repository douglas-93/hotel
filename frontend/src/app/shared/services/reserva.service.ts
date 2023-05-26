import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ReservaModel} from "../models/reserva.model";

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
}
