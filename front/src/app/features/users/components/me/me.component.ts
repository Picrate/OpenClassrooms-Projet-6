import { Component, OnInit } from '@angular/core';
import {User} from "../../interfaces/user";
import {UsersService} from "../../services/users.service";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "../../../auth/services/auth.service";
import {SessionStorageService} from "../../../../services/session-storage.service";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  public user!: User;

  constructor(
    private userService: UsersService,
    private authService: AuthService,
    private sessionStorageService: SessionStorageService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.userService.getUSerProfile().subscribe((user: User) => {
      this.user = user;
    })
  }

  unsubscribe(param: string) {

  }

  logOut() {
    this.authService.logout().subscribe(() => {
      this.sessionStorageService.logout();
      this.router.navigate(['/home']);
    });
  }
}
