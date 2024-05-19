import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SignInRequest} from "../interfaces/sign-in-request";
import {SignUpRequest} from "../interfaces/sign-up-request";

const httpOptions = {
  headers : new HttpHeaders({'Content-Type':'application/json'})
}
const API_AUTH = 'api/auth';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: SignUpRequest): Observable<any> {
    return this.httpClient.post(API_AUTH+'/register', registerRequest, httpOptions);
  }

  public login(loginRequest: SignInRequest): Observable<any> {
    return this.httpClient.post(API_AUTH+'/login', loginRequest, httpOptions);
  }

  public logout(){
    return this.httpClient.post(API_AUTH+'/logout', {}, httpOptions);
  }

}
