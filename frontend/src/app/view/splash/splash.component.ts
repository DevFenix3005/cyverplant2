import {Component, OnInit} from '@angular/core';
import {NgIf, NgStyle} from '@angular/common';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-splash',
  imports: [
    NgStyle,
    NgIf,
    MatIconModule
  ],
  templateUrl: './splash.component.html',
  styleUrl: './splash.component.scss'
})
export class SplashComponent implements OnInit {

  public windowWidth: string | undefined;
  showSplash = true;

  ngOnInit(): void {
    setTimeout(() => {
      this.windowWidth = "-" + window.innerWidth + "px";
      setTimeout(() => {
        this.showSplash = !this.showSplash;
      }, 500);
    }, 1000);
  }
}
