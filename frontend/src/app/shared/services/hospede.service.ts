import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HospedeModel} from "../models/hospede.model";

@Injectable({
	providedIn: 'root'
})
export class HospedeService {

	url: string = 'http://localhost:8080/hospedes'

	constructor(private http: HttpClient) {
	}

	getAll() {
		return this.http.get<HospedeModel[]>(this.url);
	}
}
