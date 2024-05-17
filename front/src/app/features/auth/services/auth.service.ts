import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SessionInformation} from "../../../interfaces/session-information";
import {SignInRequest} from "../interfaces/sign-in-request";
import {SignUpRequest} from "../interfaces/sign-up-request";
import {ErrorMessage} from "../../../interfaces/error-message";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private pathService = 'api/auth';

  constructor(private httpClient: HttpClient) { }

  public register(registerRequest: SignUpRequest): Observable<ErrorMessage> {
    return this.httpClient.post<ErrorMessage>(`${this.pathService}/register`, registerRequest);
  }

  public login(loginRequest: SignInRequest): Observable<SessionInformation> {
    return this.httpClient.post<SessionInformation>(`${this.pathService}/login`, loginRequest);
  }
}
