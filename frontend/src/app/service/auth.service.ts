import {Injectable, signal} from '@angular/core';
import {Location} from '@angular/common';
import {Observable, of} from 'rxjs';
import {map, catchError} from 'rxjs/operators';
import {UserResourceService, UserDto} from '../client'

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    user = signal<UserDto | null>(null);
    isAuthenticated = signal(false)

    constructor(private readonly userResourceService: UserResourceService, private readonly location: Location) {
    }

    getUser(): Observable<UserDto> {
        return this.userResourceService.getUser()
            .pipe(map((response) => {
                    if (response !== null) {
                        console.log(response);
                        this.user.set(response)
                        this.isAuthenticated.set(true);
                    }
                    return response;
                }),
                catchError(this.handleError<UserDto>('getUser', {nickname: "", email: "", name: "", picture: ""})));
    }

    login(): void {
        location.href = `${location.origin}${this.location.prepareExternalUrl('oauth2/authorization/okta')}`;
    }

    logout(): void {
        this.userResourceService.logout().subscribe((response: any) => {
            location.href = response.logoutUrl;
        });
    }

    protected handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            console.error(error); // log to console instead
            console.log(`${operation} failed: ${error.message}`);
            return of(result as T);
        };
    }

}
