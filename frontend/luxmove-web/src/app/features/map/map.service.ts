import { Injectable } from '@angular/core';
import { MobilityLocation } from './mobility-location';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private readonly apiUrl = "http://localhost:8080/api/locations";

  constructor(private readonly http: HttpClient){}

  getLocations(): Observable<MobilityLocation[]> {
    return this.http.get<MobilityLocation[]>(this.apiUrl);
  }
}