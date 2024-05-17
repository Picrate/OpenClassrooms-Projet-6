import { Component, OnInit } from '@angular/core';
import {UsersService} from "../../../../services/users.service";
import {Post} from "../../../posts/interfaces/post";
import {Router} from "@angular/router";

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.scss']
})
export class FeedComponent implements OnInit {

  constructor(private userService: UsersService, private router: Router) { }

  myFeedList$ = this.userService.getMyFeed();

  ngOnInit(): void {}

  addArticle() {
    this.router.navigate(['/posts/create']);
  }
}
