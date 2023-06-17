import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HotelModel} from "../models/hotel.model";
import {ProdutoModel} from "../models/produto.model";

@Injectable({
	providedIn: 'root'
})
export class ProdutoService {

	url: string = 'http://localhost:8080/produtos';

	constructor(private http: HttpClient) {
	}

	getAll() {
		return this.http.get<ProdutoModel[]>(this.url, {observe: 'response'})
	}

	createProduto(produto: ProdutoModel) {
		return this.http.post(this.url, produto, {observe: 'response'})
	}

	createProdutos(produtos: ProdutoModel[]) {
		return this.http.post(`${this.url}/m`, produtos, {observe: 'response'})
	}
}
