import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PlantDto} from '../model';
import {environment} from '../../environments/environment'

@Injectable({
  providedIn: 'root'
})
export class PlantService {

  private readonly api = environment.api;

  constructor(private readonly _http: HttpClient) {
  }

  getPlant(ownerId: string): Observable<PlantDto[]> {
    return this._http.get<PlantDto[]>(`${this.api}/plant`, {params: {ownerId}});
  }


}
