import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from "rxjs";
import {SessionInformation} from "../interfaces/session-information";
import {StorageService} from "./storage.service";
import {User} from "../features/users/interfaces/user";

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  private isAuthenticated = false;
  public sessionInformation: SessionInformation | undefined;

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(this.isAuthenticated);

  constructor(private storageService: StorageService) { }

  public $isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }

  public logIn(user: SessionInformation): void {
    this.sessionInformation = user;
    this.isAuthenticated = true;
    this.storageService.saveUser(user);
    this.next();
  }

  public logOut(): void {
    this.storageService.clean();
    this.isAuthenticated = this.storageService.isLoggedIn();
    this.next();
  }

  private next(): void {
    this.isAuthenticatedSubject.next(this.isAuthenticated);
  }

  public getCurrentUserInformations(): User | undefined{
    return this.sessionInformation?.user;
}

}
