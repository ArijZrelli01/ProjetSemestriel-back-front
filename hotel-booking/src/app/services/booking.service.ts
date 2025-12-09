import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class BookingService {
  private baseUrl = `${environment.bookingApiUrl}/bookings`;

  constructor(private http: HttpClient) {}

  getAllBookings(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  createBooking(booking: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, booking);
  }

  getBookings(filter?: { phone?: string; date?: string }): Observable<any[]> {
  let params = new HttpParams();
  if (filter?.phone) params = params.set('phone', filter.phone);
  if (filter?.date) params = params.set('date', filter.date);

  return this.http.get<any[]>(this.baseUrl, { params });
}
deleteBooking(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }
}
