import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {HotelModel} from "../models/hotel.model";

@Injectable({
	providedIn: 'root'
})
export class HotelService {

	url: string = 'http://localhost:8080/hoteis';

	constructor(private http: HttpClient) {
	}

	getLastHotelVersion() {
		return this.http.get<HotelModel>(`${this.url}/get/last`, {observe: 'response'})
	}

	createHotel(hotel: HotelModel) {
		return this.http.post(this.url, hotel, {observe: 'response'})
	}
}
