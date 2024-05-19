import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../services/users.service";
import {Post} from "../../../posts/interfaces/post";
import {Router} from "@angular/router";

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  constructor(private userService: UsersService, private router: Router) { }

  myFeedList$ = this.userService.getUserFeed();

  ngOnInit(): void {
    this.myFeedList$ = this.userService.getUserFeed();
  }

  addArticle() {
    this.router.navigate(['/posts/create']);
  }
}
