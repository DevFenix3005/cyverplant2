import {Component, Input} from '@angular/core';
import {MatCardModule} from '@angular/material/card';

@Component({
  selector: 'app-dashboard-statistics',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './dashboard-statistics.component.html',
  styleUrl: './dashboard-statistics.component.scss'
})
export class DashboardStatisticsComponent {
  @Input() plantCount = 0;
}
