import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {SessionService} from "../services/session.service";
import {StorageService} from "../services/storage.service";

@Injectable({ providedIn: "root"})
export class JwtInterceptor implements HttpInterceptor {
  constructor(private sessionService: SessionService, private storageService: StorageService) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    request = request.clone({
      withCredentials: true,
    });
    return next.handle(request);
  }
}

export const httpInterceptorProviders = [
  {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
]
