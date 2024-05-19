import { Component, OnInit } from '@angular/core';
import {PostsApiService} from "../../services/posts-api.service";
import {Observable} from "rxjs";
import {Topic} from "../../interfaces/topic";
import {UsersService} from "../../../users/services/users.service";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss']
})
export class TopicListComponent implements OnInit {

  public topics$?: Observable<Topic[]>;

  constructor(private postService: PostsApiService, private userService:UsersService) { }

  ngOnInit(): void {
    this.topics$ = this.postService.getTopics();
  }

  subscribeTopic(topic: Topic) {
    this.userService.subscribeToTopic(topic);
  }
}
