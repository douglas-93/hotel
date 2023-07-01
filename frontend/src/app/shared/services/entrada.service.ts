import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EntradaModel} from "../models/entrada.model";

@Injectable({
	providedIn: 'root'
})
export class EntradaService {

	url: string = 'http://localhost:8080/estoque/entrada'

	constructor(private http: HttpClient) {
	}

	getEntradas() {
		return this.http.get<EntradaModel[]>(`${this.url}s`);
	}

	getEntrada(id: number) {
		return this.http.get<EntradaModel>(`${this.url}/${id}`, {observe: 'response'});
	}


	createEntrada(entrada: EntradaModel) {
		entrada.tipo = 'Entrada';
		return this.http.post<EntradaModel>(this.url, entrada, {observe: 'response'});
	}

	updateEntrada(entrada: EntradaModel) {
		return this.http.put(`${this.url}/${entrada.id}`, entrada, {observe: 'response'});
	}

	deleteEntrada(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}
}
