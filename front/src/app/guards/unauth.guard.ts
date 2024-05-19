import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {SessionStorageService} from "../services/session-storage.service";
import {map, Observable, take} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UnauthGuard implements CanActivate {

  constructor(private router: Router, private storageService: SessionStorageService) {}

  canActivate(): Observable<boolean> {
    return this.storageService.isLoggedIn().pipe(
      take(1),
      map((isLoggedIn: boolean) => {
        if(isLoggedIn){
          this.router.navigate(['/users/feed']);
          return false;
        }
        return true;
      })
    );
  }

}
