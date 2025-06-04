import {Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {DatePipe, UpperCasePipe} from '@angular/common';
import {PlantWithLastEnvironmentalMeasurement} from '../../client';

@Component({
  selector: 'app-plant-list',
  standalone: true,
  imports: [MatCardModule, MatChipsModule, MatDividerModule, MatButtonModule, DatePipe, UpperCasePipe],
  templateUrl: './plant-list.component.html',
  styleUrl: './plant-list.component.scss'
})
export class PlantListComponent {
  @Input() plants: PlantWithLastEnvironmentalMeasurement[] = [];
}
