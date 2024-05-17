import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../features/users/interfaces/user";
import {Post} from "../features/posts/interfaces/post";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private pathService = 'api/users';

  constructor(private httpClient: HttpClient) { }

  public getById(id: string | undefined): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/${id}`);
  }

  public getMyProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public getMyFeed(): Observable<Array<Post>> {
    return this.httpClient.get<Array<Post>>(`${this.pathService}/feed`);
  }

}
