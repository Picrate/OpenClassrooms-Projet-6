import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {User} from "../interfaces/user";
import {Post} from "../../posts/interfaces/post";
import {Topic} from "../../posts/interfaces/topic";
import {ResponseMessage} from "../../../interfaces/response-message";
import {UpdateProfileRequest} from "../interfaces/update-profile-request";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private pathService = 'api/users';

  constructor(private httpClient: HttpClient) { }

  public getUserProfile(): Observable<User> {
    return this.httpClient.get<User>(`${this.pathService}/me`);
  }

  public getUserFeed(): Observable<Post[]> {
    return this.httpClient.get<Post[]>(`${this.pathService}/feed`);
  }

  public getUserTopics(): Observable<Array<Topic>> {
    return this.httpClient.get<Array<Topic>>(`${this.pathService}/topics`);
  }

  public manageTopicSubscription(topic: Topic){
    return this.httpClient.put<any>(`${this.pathService}/topics`, topic);
  }

  public updateProfile(updateProfile: UpdateProfileRequest): Observable<ResponseMessage>{
    return this.httpClient.put<ResponseMessage>(`${this.pathService}/me`, updateProfile);
  }

  public isUsernameOrEmailAlreadyExists(usernameOrEmail: string): Observable<ResponseMessage>{
    if(usernameOrEmail === null){
      return of({message: "false"});
    } else {
      let request = {
        usernameOrEmail : usernameOrEmail
      }
      return this.httpClient.post<ResponseMessage>(`${this.pathService}/exists`, request);
    }
  }
}
