import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../interfaces/user";
import {Post} from "../../posts/interfaces/post";
import {Topic} from "../../posts/interfaces/topic";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private pathService = 'api/users';

  constructor(private httpClient: HttpClient) { }

  public getUSerProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public getUserFeed(): Observable<Array<Post>> {
    return this.httpClient.get<Array<Post>>(`${this.pathService}/feed`);
  }

  public subscribeToTopic(topic: Topic){
    return this.httpClient.put<any>(`${this.pathService}/topics`, topic);
  }

}
