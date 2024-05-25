import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {JwtResponse} from "../interfaces/jwt-response";
import {TokenRefreshRequest} from "../interfaces/token-refresh-request";

const USER_KEY = 'auth-user';
const TOKEN_KEY = 'mdd-token';

@Injectable({
  providedIn: 'root'
})
export class SessionStorageService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  constructor() { }

  logout():void{
    window.sessionStorage.clear();
    window.localStorage.clear();
    this.loggedIn.next(false);

  }

  public saveUser(jwtResponse: JwtResponse): void {
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(jwtResponse.user));
    this.setRefreshToken(jwtResponse.refreshToken);
    this.loggedIn.next(true);
  }

  public getUser(): any{
    const userSession = window.sessionStorage.getItem(USER_KEY);
    if(userSession){
      return JSON.parse(userSession);
    }
    return {};
  }

  public isLoggedIn(): Observable<boolean> {
    return this.loggedIn.asObservable();
  }

  public hasRefreshTokenOnLocalStorage(){
    const currentToken = window.localStorage.getItem(TOKEN_KEY);
    return currentToken !== null;
  }

  public getRefreshToken(): TokenRefreshRequest{
    let token: string | null = '';
    if(this.hasRefreshTokenOnLocalStorage()) {
      token = window.localStorage.getItem(TOKEN_KEY);
    }
    let refreshTokenRequest:TokenRefreshRequest = {
      refreshToken :token
    }
    return refreshTokenRequest;
  }

  public setRefreshToken(token:string){
    if(token !== null && token.length > 0){
      window.localStorage.setItem(TOKEN_KEY, token);
    }
  }


}
