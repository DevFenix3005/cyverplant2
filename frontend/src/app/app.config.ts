import {ApplicationConfig, inject, provideAppInitializer, provideZoneChangeDetection} from '@angular/core';
import {provideHttpClient, withFetch} from '@angular/common/http'
import {provideRouter} from '@angular/router';
import {MatIconRegistry} from '@angular/material/icon';
import {DomSanitizer} from '@angular/platform-browser';
import {firstValueFrom} from 'rxjs';

import {routes} from './app.routes';
import {AuthService} from './service/auth.service';


export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes),
    provideHttpClient(withFetch()),
    provideAppInitializer(() => {
      const iconRegistry: MatIconRegistry = inject(MatIconRegistry)
      const domSanitizer: DomSanitizer = inject(DomSanitizer)
      const auth: AuthService = inject(AuthService)
      iconRegistry.addSvgIcon('sprout', domSanitizer.bypassSecurityTrustResourceUrl('assets/icons/sprout.svg'))
      return firstValueFrom(auth.getUser());
    })
  ]
};
