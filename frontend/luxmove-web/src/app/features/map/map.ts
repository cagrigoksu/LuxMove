import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import * as L from 'leaflet';

@Component({
  selector: 'app-map',
  imports: [],
  templateUrl: './map.html',
  styleUrl: './map.css',
})
export class Map implements AfterViewInit {
  @ViewChild('map', { static: true })
  mapElement!: ElementRef<HTMLDivElement>;

  ngAfterViewInit(): void {
    const map = L.map(this.mapElement.nativeElement).setView(
      [49.6116, 6.1319],
      12
    );

    setTimeout(() => {
      map.invalidateSize();
    }, 0);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; OpenStreetMap contributors',
    }).addTo(map);
  }
}