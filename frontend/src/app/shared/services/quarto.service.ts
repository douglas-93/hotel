import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {QuartoModel} from "../models/quarto.model";

@Injectable({
	providedIn: 'root'
})
export class QuartoService {

	urlEndPoint: string = "http://localhost:8080/quartos";
	constructor(private http: HttpClient) { }

	getQuartos(){
		return this.http.get<QuartoModel[]>(this.urlEndPoint, {observe: 'response'});
	}


	getQuarto(id: number){
		return this.http.get<QuartoModel>(`${this.urlEndPoint}/${id}`, {observe: 'response'});
	}

	createQuarto(quarto: QuartoModel){
		// return this.http.post(this.urlEndPoint, quarto, {observe: 'response'});
		const formData = new FormData();
		formData.append('nome', quarto.nome!);
		formData.append('tipo', quarto.tipo!.toString());
		formData.append('categoria', quarto.categoria!.toString());
		formData.append('ativo', quarto.ativo!.toString());
		formData.append('imagem', quarto.imagem!);

		return this.http.post<QuartoModel>(this.urlEndPoint, formData, {observe: 'response'});
	}

	updateQuarto(quarto: QuartoModel){
		const formData = new FormData();
		formData.append('nome', quarto.nome!);
		formData.append('tipo', quarto.tipo!.toString());
		formData.append('categoria', quarto.categoria!.toString());
		formData.append('ativo', quarto.ativo!.toString());
		formData.append('imagem', quarto.imagem!);

		return this.http.put(`${this.urlEndPoint}/${quarto.id}`, formData, {observe: 'response'});
	}


	deleteQuarto(id: number){
		return this.http.delete(`${this.urlEndPoint}/${id}`, {observe: 'response'});
	}
}
