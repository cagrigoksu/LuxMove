import { Injectable } from '@angular/core';
import { MobilityLocation } from './mobility-location';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MapService {

  private readonly locations: MobilityLocation[] = [
    {
      name: 'Luxembourg City',
      latitude: 49.6116,
      longitude: 6.1300,
    },
    {
      name: 'Luxembourg Gare',
      latitude: 49.6008,
      longitude: 6.1347,
    }
  ];

  getLocations(): Observable<MobilityLocation[]> {
    return of(this.locations);
  }
}