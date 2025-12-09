import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private baseUrl = `${environment.clientApiUrl}/clients`;

  constructor(private http: HttpClient) {}

  getAllClients(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

   
  findByPhone(phone: string): Observable<any> {
  return this.http.get<any>(`${this.baseUrl}/search`, { params: { phone } });
}


  addClient(client: any): Observable<any> {
    return this.http.post<any>(this.baseUrl, client);
  }
}
