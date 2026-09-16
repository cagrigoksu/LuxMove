import { Routes } from '@angular/router';
import { Home } from './features/home/home';
import { Map } from './features/map/map';
import { Stations } from './features/stations/stations';
import { Routes as RoutesPage } from './features/routes/routes';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path:'map',
        component:Map
    },
    {
        path:'stations',
        component:Stations
    },
    {
        path:'routes',
        component:RoutesPage
    }
];
