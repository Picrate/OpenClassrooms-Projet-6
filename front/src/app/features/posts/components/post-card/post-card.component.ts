import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../interfaces/post";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.scss']
})
export class PostCardComponent implements OnInit {

  @Input() post!: Post;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  openPost(post: Post) {
    this.router.navigate([`/posts/${post.id}`]);
  }

  protected readonly onkeydown = onkeydown;
  protected readonly KeyboardEvent = KeyboardEvent;
}
