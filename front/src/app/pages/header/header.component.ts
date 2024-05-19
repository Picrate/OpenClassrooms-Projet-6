import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {SessionStorageService} from "../../services/session-storage.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$ ?: Observable<boolean>;

  constructor(private storageService: SessionStorageService, public router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn$ = this.storageService.isLoggedIn();
  }

  goToHome() {
    this.router.navigate(['/users/feed']);
  }

  myProfile() {
    this.router.navigate(['/users/me']);
  }
}
