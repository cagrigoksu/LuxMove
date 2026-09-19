import { MobilityLocationType } from "./mobility-location-type";

export interface MobilityLocation {
  name: string;
  latitude: number;
  longitude: number;
  type: MobilityLocationType;
}