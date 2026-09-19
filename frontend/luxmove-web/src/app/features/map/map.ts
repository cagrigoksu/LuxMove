import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import * as L from 'leaflet';
import { MapService } from './map.service';
import { MobilityLocationType } from './mobility-location-type';

@Component({
  selector: 'app-map',
  imports: [],
  templateUrl: './map.html',
  styleUrl: './map.css',
})
export class Map implements AfterViewInit {

    private getLocationTypeLabel(type: MobilityLocationType): string {
    switch (type) {
      case 'TRAIN_STATION':
        return 'Train station';

      case 'BUS_STOP':
        return 'Bus stop';

      case 'BIKE_STATION':
        return 'Bike station';

      case 'PARKING':
        return 'Parking';

      case 'EV_CHARGER':
        return 'EV charger';

      case 'CAR_SHARING':
        return 'Car sharing';

      case 'MOBILITY_HUB':
        return 'Mobility hub';
    }
  }

  constructor(private readonly mapService: MapService){};

  @ViewChild('map', { static: true })
  mapElement!: ElementRef<HTMLDivElement>;

  ngAfterViewInit(): void {

    this.mapService.getLocations().subscribe((locations) => {

      const map = L.map(this.mapElement.nativeElement).setView(
        [locations[0].latitude, locations[0].longitude],
        12
      );

      setTimeout(() => {
        map.invalidateSize();
      }, 0);

      L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; OpenStreetMap contributors',
      }).addTo(map);

      const mobilityIcon = L.icon({
        iconUrl: '/leaflet/marker-icon.png',
        iconRetinaUrl: '/leaflet/marker-icon-2x.png',
        shadowUrl: '/leaflet/marker-shadow.png',

        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41],
      });

      for (const location of locations) {
        L.marker(
          [location.latitude, location.longitude],
          { icon: mobilityIcon }
        )
          .addTo(map)
          .bindPopup(`
            <strong>${location.name}</strong><br>
            ${this.getLocationTypeLabel(location.type)}
          `);
      }
    
    });
  }
}