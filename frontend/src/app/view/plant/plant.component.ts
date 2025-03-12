import {Component, inject, OnInit} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button'
import {PlantResourceService, PlantDto} from '../../client';
import {DatePipe, UpperCasePipe} from '@angular/common';


@Component({
  selector: 'app-plant',
  imports: [MatCardModule, MatChipsModule, MatProgressBarModule, MatDividerModule, MatButtonModule, DatePipe, UpperCasePipe],
  templateUrl: './plant.component.html',
  styleUrl: './plant.component.scss'
})
export class PlantComponent implements OnInit {

  private readonly plantResourceService: PlantResourceService = inject(PlantResourceService)
  plants: PlantDto[] = []

  ngOnInit(): void {
    this.plantResourceService.getPlantsByOwner().subscribe(plants => {
      console.log(plants);
      this.plants = plants
    })
  }

}
