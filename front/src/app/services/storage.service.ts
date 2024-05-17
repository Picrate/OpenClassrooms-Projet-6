import { Injectable } from '@angular/core';
import {SessionInformation} from "../interfaces/session-information";

const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  clean():void{
    window.sessionStorage.clear();
  }

  public saveUser(sessionInformation: SessionInformation): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(sessionInformation));
  }

  public getUser(): SessionInformation | undefined{
    const session = window.sessionStorage.getItem(USER_KEY);
    if(session){
      return JSON.parse(session);
    }
    return undefined;
  }

  public isLoggedIn(): boolean {
    const session = window.sessionStorage.getItem(USER_KEY);
    if (session) {
      return true;
    }
    return false;
  }
}
