import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class HotelService {
  private baseUrl = `${environment.hotelApiUrl}/hotels`;

  constructor(private http: HttpClient) {}

  getAllHotels() {
    return this.http.get<any[]>(this.baseUrl);
  }

  getHotelById(id: number) {
    return this.http.get(`${this.baseUrl}/${id}`);
  }
}


