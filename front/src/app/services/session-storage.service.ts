import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {SessionInformation} from "../interfaces/session-information";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor() { }

  logout():void{
    window.sessionStorage.clear();
    this.loggedIn.next(false);
  }

  public saveUser(sessionInformation: SessionInformation): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(sessionInformation));
    this.loggedIn.next(true);
  }

  public getUser(): SessionInformation | undefined{
    const userSession = window.sessionStorage.getItem(USER_KEY);
    if(userSession){
      return JSON.parse(userSession);
    }
    return undefined;
  }

  public isLoggedIn(): Observable<boolean> {
    console.log(this.loggedIn);
    return this.loggedIn.asObservable();
  }
}
