import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {CidadeModel} from "../models/cidade.model";

@Injectable({
  providedIn: 'root'
})
export class CidadeService {

	url: string = 'https://servicodados.ibge.gov.br/api/v1/localidades/estados/{UF}/distritos?view=nivelado'

  constructor(private http: HttpClient) { }

	getCidades(id) {
		return this.http.get<CidadeModel[]>(this.url.replace('{UF}', id))
	}
}
