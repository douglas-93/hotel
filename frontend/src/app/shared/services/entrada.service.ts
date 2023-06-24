import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EntradaModel} from "../models/entrada.model";

@Injectable({
	providedIn: 'root'
})
export class EntradaService {

	url: string = 'http://localhost:8080/entradas'

	constructor(private http: HttpClient) {
	}

	getEntradas() {
		return this.http.get<EntradaModel[]>(this.url);
	}

	getEntrada(id: number) {
		return this.http.get<EntradaModel>(`${this.url}/${id}`, {observe: 'response'});
	}


	createEntrada(hospede: EntradaModel) {
		return this.http.post<EntradaModel>(this.url, hospede, {observe: 'response'});
	}

	updateEntrada(hospede: EntradaModel) {
		return this.http.put(`${this.url}/${hospede.id}`, hospede, {observe: 'response'});
	}

	deleteEntrada(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}
}
