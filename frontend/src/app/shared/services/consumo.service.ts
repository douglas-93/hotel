import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ConsumoModel} from "../models/consumo.model";

@Injectable({
	providedIn: 'root'
})
export class ConsumoService {

	url: string = 'http://localhost:8080/consumo'

	constructor(private http: HttpClient) {
	}

	getConsumos() {
		return this.http.get<ConsumoModel[]>(`${this.url}`);
	}

	getConsumo(id: number) {
		return this.http.get<ConsumoModel[]>(`${this.url}/${id}`, {observe: 'response'});
	}


	createConsumo(consumo: ConsumoModel) {
		return this.http.post<ConsumoModel>(this.url, consumo, {observe: 'response'});
	}

	updateConsumo(consumo: ConsumoModel) {
		return this.http.put(`${this.url}/${consumo.id}`, consumo, {observe: 'response'});
	}

	deleteConsumo(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}
}
