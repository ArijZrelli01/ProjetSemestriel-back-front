import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  private baseUrl = `${environment.roomApiUrl}/rooms`;

  constructor(private http: HttpClient) { }

  

   getRoomsByHotel(hotelId: number): Observable<any[]> {
        console.log('Fetching rooms for hotelId=', hotelId);  

    return this.http.get<any[]>(`${this.baseUrl}?hotelId=${hotelId}`);
  }
}
