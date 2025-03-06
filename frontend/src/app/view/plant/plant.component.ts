import {Component, inject, OnInit} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatDividerModule} from '@angular/material/divider';
import {PlantService} from '../../services/plant.service';
import {PlantDto} from '../../model';

@Component({
  selector: 'app-plant',
  imports: [MatCardModule, MatChipsModule, MatProgressBarModule, MatDividerModule],
  templateUrl: './plant.component.html',
  styleUrl: './plant.component.scss'
})
export class PlantComponent implements OnInit {

  private readonly plantService: PlantService = inject(PlantService)
  plants: PlantDto[] = []

  ngOnInit(): void {
    this.plantService.getPlant("f9312fe2-6d9f-45a7-a2d9-c9dbe34941bc").subscribe(plants => {
      console.log(plants);
      this.plants = plants
    })
  }

}
