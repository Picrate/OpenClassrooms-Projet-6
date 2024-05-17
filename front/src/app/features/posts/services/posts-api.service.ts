import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../interfaces/post";
import {NewPostRequest} from "../interfaces/new-post-request";
import {SessionService} from "../../../services/session.service";
import {UsersService} from "../../../services/users.service";
import {User} from "../../users/interfaces/user";
import {ErrorMessage} from "../../../interfaces/error-message";
import {Topic} from "../interfaces/topic";

@Injectable({
  providedIn: 'root'
})
export class PostsApiService {

  private pathService = 'api/posts';
  private user: User | undefined;

  constructor(private httpClient: HttpClient, private sessionService: SessionService, private userService: UsersService) { }

  public getById(postId: string): Observable<Post>{
    return this.httpClient.get<Post>(`${this.pathService}/${postId}`);
  }

  public getByTopic(topic: string): Observable<Array<Post>> {
    const parameter = topic.trim();
    const options =
      parameter
        ? {params: new HttpParams().set('topic', parameter)}
        : {};
    return this.httpClient.get<Array<Post>>(`${this.pathService}/topic`, options);
  }

  public createNewPost(newPost: NewPostRequest): void {
    this.httpClient.post<ErrorMessage>(`${this.pathService}`, newPost);
  }

  public getTopics(): Observable<Array<Topic>>{
    return this.httpClient.get<Array<Topic>>(`${this.pathService}/topics`);
  }
}
