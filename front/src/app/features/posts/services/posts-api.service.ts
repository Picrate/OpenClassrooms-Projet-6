import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "../interfaces/post";
import {NewPostRequest} from "../interfaces/new-post-request";
import {ResponseMessage} from "../../../interfaces/response-message";
import {Topic} from "../interfaces/topic";
import {NewPostComment} from "../interfaces/new-post-comment";

@Injectable({
  providedIn: 'root'
})
export class PostsApiService {

  private postPathService = 'api/posts';
  private commentPathService = 'api/comments';

  constructor(private httpClient: HttpClient) { }

  public getById(postId: string): Observable<Post>{
    return this.httpClient.get<Post>(`${this.postPathService}/${postId}`);
  }

  public getPosts(topic: string): Observable<Array<Post>> {
    const parameter = topic.trim();
    const options =
      parameter
        ? {params: new HttpParams().set('topic', parameter)}
        : {};
    return this.httpClient.get<Array<Post>>(`${this.postPathService}/topic`, options);
  }

  public createNewPost(newPost: NewPostRequest): Observable<ResponseMessage> {
    return this.httpClient.post<ResponseMessage>(`${this.postPathService}`, newPost);
  }

  public postNewComment(newComment: NewPostComment):Observable<any> {
    return this.httpClient.post<ResponseMessage>(`${this.commentPathService}`, newComment);
  }

  public getTopics(): Observable<Array<Topic>>{
    const options =
      {params: new HttpParams().set('title', '')}
    return this.httpClient.get<Array<Topic>>(`${this.postPathService}/topics`, options);
  }

  public getTopicsByTitle(title: string): Observable<Array<Topic>> {
    const parameter = title.trim();
    const options =
      parameter
        ? {params: new HttpParams().set('title', parameter)}
        : {};
    return this.httpClient.get<Array<Topic>>(`${this.postPathService}/topics`, options);
  }
}
