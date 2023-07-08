import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CupomConsumoModel} from "../models/cupomConsumo.model";

@Injectable({
	providedIn: 'root'
})
export class CupomConsumoService {
	url: string = 'http://localhost:8080/cupons-consumo'

	constructor(private http: HttpClient) {
	}

	getCupons() {
		return this.http.get<CupomConsumoModel[]>(`${this.url}`);
	}

	getCupom(id: number) {
		return this.http.get<CupomConsumoModel[]>(`${this.url}/${id}`, {observe: 'response'});
	}


	createCupom(id: number) {
		return this.http.post<CupomConsumoModel>(`${this.url}/${id}`, {observe: 'response'});
	}

	updateCupom(cupom: CupomConsumoModel) {
		return this.http.put(`${this.url}/${cupom.id}`, cupom, {observe: 'response'});
	}

	deleteCupom(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}
}
