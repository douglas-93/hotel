import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
	providedIn: 'root'
})
export class RelatoriosService {

	url: string = 'http://localhost:8080/relatorios'

	constructor(private http: HttpClient) {
	}

	relatorioHospedes() {
		return this.http.get(`${this.url}/hospedes`, {
			responseType: 'arraybuffer',
			observe: 'response',
		})
	}
}
