import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {SessionService} from "../services/session.service";
import {StorageService} from "../services/storage.service";

@Injectable({ providedIn: "root"})
export class JwtInterceptor implements HttpInterceptor {

  constructor(private sessionService: SessionService, private storageService: StorageService) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    if(this.sessionService.$isAuthenticated()){
      request = request.clone({
        setHeaders: {
          Authorization: `Bearer ${this.sessionService.sessionInformation?.token}`,
        },
      })
    }
    return next.handle(request);
  }
}
