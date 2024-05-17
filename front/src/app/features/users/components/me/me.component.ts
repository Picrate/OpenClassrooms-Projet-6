import { Component, OnInit } from '@angular/core';
import {User} from "../../interfaces/user";
import {UsersService} from "../../../../services/users.service";
import {SessionService} from "../../../../services/session.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user!: User;

  constructor(
    private userService: UsersService,
    private sessionService: SessionService,
    private router: Router,
    private httpClient: HttpClient
  ) { }

  ngOnInit(): void {
    this.userService.getMyProfile().subscribe((user: User) => {
      this.user = user;
    })
  }

  unsubscribe(param: string) {

  }

  logOut() {
    this.sessionService.logOut();
    this.router.navigate(['']);
  }
}
