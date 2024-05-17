import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {StorageService} from "../services/storage.service";

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private storageService: StorageService) {}

  canActivate(): boolean {
    if (!this.storageService.isLoggedIn()) {
      this.router.navigate(['/auth/signin']);
      return false;
    }
    return true;
  }

}

