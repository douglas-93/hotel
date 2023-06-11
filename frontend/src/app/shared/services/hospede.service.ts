import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HospedeModel} from "../models/hospede.model";
import _ from "lodash";

@Injectable({
	providedIn: 'root'
})
export class HospedeService {

	url: string = 'http://localhost:8080/hospedes'

	constructor(private http: HttpClient) {
	}

	getHospedes() {
		return this.http.get<HospedeModel[]>(this.url);
	}

	getHospede(id: number) {
		return this.http.get<HospedeModel>(`${this.url}/${id}`, {observe: 'response'});
	}

	getHospedesByNomeOrCpf(nome: string, cpf: string) {
		let url = `${this.url}/data`

		if (!_.isEmpty(nome) && !_.isEmpty(cpf)) {
			url += `?nome=${encodeURIComponent(nome)}&cpf=${encodeURIComponent(cpf)}`
		} else if (!_.isEmpty(nome)) {
			url += `?nome=${encodeURIComponent(nome)}`
		} else if (!_.isEmpty(cpf)) {
			url += `?cpf=${encodeURIComponent(cpf)}`
		}

		return this.http.get<HospedeModel[]>(url, {observe: 'response'})
	}


	createHospede(hospede: HospedeModel) {
		return this.http.post<HospedeModel>(this.url, hospede, {observe: 'response'});
	}

	updateHospede(hospede: HospedeModel) {
		return this.http.put(`${this.url}/${hospede.id}`, hospede, {observe: 'response'});
	}

	deleteHospede(id: number) {
		return this.http.delete(`${this.url}/${id}`, {observe: 'response'});
	}
}
