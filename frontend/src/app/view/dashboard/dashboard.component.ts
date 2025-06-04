import {Component, inject, OnInit} from '@angular/core';
import {PlantResourceService, PlantWithLastEnvironmentalMeasurement} from '../../client';
import {PlantListComponent} from './plant-list.component';
import {DashboardStatisticsComponent} from './dashboard-statistics.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [PlantListComponent, DashboardStatisticsComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  private readonly plantResourceService: PlantResourceService = inject(PlantResourceService);
  plants: PlantWithLastEnvironmentalMeasurement[] = [];

  ngOnInit(): void {
    this.plantResourceService.getPlantsByOwner().subscribe(plants => {
      this.plants = plants;
    });
  }
}
