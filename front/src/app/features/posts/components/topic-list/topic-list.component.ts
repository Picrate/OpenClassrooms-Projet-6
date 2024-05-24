import { Component, OnInit } from '@angular/core';
import {PostsApiService} from "../../services/posts-api.service";
import {Observable} from "rxjs";
import {Topic} from "../../interfaces/topic";
import {UsersService} from "../../../users/services/users.service";
import {SessionInformation} from "../../../../interfaces/session-information";
import {SessionStorageService} from "../../../../services/session-storage.service";

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.scss']
})
export class TopicListComponent implements OnInit {

  public topics$?: Observable<Topic[]>;
  public userTopics:Topic[] = [];
  private userInfos: SessionInformation | undefined;

  constructor(private postService: PostsApiService, private userService:UsersService, private storageService: SessionStorageService) { }

  ngOnInit(): void {
    this.topics$ = this.postService.getTopics();
    this.userInfos = this.storageService.getUser();

    if (this.userInfos !== undefined) {
      this.userService.getUserTopics().subscribe(topics => {
        this.userTopics = topics;
      });
    }
  }

  isTopicSubscribed(currentTopic: Topic):string {
    if(this.userTopics.find(userTopic => userTopic.id === currentTopic.id)) {
      return 'Se désabonner';
    } else {
      return 'S\'abonner';
    }
  }

}
