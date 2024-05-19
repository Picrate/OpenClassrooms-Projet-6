import {Injectable, OnDestroy, OnInit} from '@angular/core';
import {SessionStorageService} from "../services/session-storage.service";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {map, Observable, take} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private storageService : SessionStorageService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {
    return this.storageService.isLoggedIn().pipe(take(1),map((isLoggedIn) => {
      console.log(isLoggedIn);
      if(!isLoggedIn){
        this.router.navigate(['/']);
        return false;
      }
      return true;
    }));
  }
}

