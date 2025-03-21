import {Routes} from '@angular/router';
import {DashboardComponent} from './view/dashboard/dashboard.component'
import {PlantComponent} from './view/plant/plant.component'
import {NotFoundComponent} from './view/not-found/not-found.component'


export const routes: Routes = [
  {path: "dashboard", component: DashboardComponent, canActivate: []},
  {path: "plant", component: PlantComponent, canActivate: []},
  {path: "", redirectTo: 'dashboard', pathMatch: 'full'},
  {path: "**", component: NotFoundComponent, canActivate: []}
];
