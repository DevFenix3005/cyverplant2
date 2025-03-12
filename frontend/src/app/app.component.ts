import {Component, computed, inject, OnInit, effect} from '@angular/core';
import {RouterOutlet, RouterLink} from '@angular/router';
import {MatToolbarModule} from '@angular/material/toolbar'
import {MatButtonModule} from '@angular/material/button'
import {MatIconModule} from '@angular/material/icon'
import {MatMenuModule} from '@angular/material/menu'
import {MatCardModule} from '@angular/material/card';
import {MatDividerModule} from '@angular/material/divider';
import {HttpClient} from '@angular/common/http';

import {SplashComponent} from './view/splash/splash.component'
import {AuthService} from './service/auth.service';
import {NgOptimizedImage} from '@angular/common';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, MatToolbarModule, MatButtonModule, MatIconModule, MatMenuModule, MatCardModule, MatDividerModule, SplashComponent, NgOptimizedImage],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnInit {
  title = 'Cyver | Plant Monitoring';
  protected auth: AuthService = inject(AuthService)
  protected http: HttpClient = inject(HttpClient)
  protected nickname = computed(() => {
    return this.auth.user()?.nickname;
  })
  protected picture = computed(() => {
    return this.auth.user()?.picture
  })


  ngOnInit(): void {
    if (!this.auth.isAuthenticated()) {
      this.auth.login();
    }
  }

}
