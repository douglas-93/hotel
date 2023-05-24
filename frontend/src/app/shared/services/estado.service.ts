import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {EstadoModel} from "../models/estado.model";

@Injectable({
  providedIn: 'root'
})
export class EstadoService {

	url: string = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados?view=nivelado'

  constructor(private http: HttpClient) { }

	getEstados() {
		return this.http.get<any[]>(this.url)
	}
}
