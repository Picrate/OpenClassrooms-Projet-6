import {Component, Input, OnInit} from '@angular/core';
import {PostsApiService} from "../../services/posts-api.service";
import {Post} from "../../interfaces/post";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.scss']
})
export class PostListComponent implements OnInit {

  public posts$ = this.postService.getByTopic('topic');
  @Input() postList$!: Array<Post> | undefined;

  constructor(private postService: PostsApiService) { }

  ngOnInit(): void {
  }

}
