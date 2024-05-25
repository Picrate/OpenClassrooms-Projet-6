import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {SessionStorageService} from "../../services/session-storage.service";
import {AuthService} from "../../features/auth/services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit {
  constructor(private router: Router, private sessionStorage: SessionStorageService, private authService: AuthService) {}

  ngOnInit(): void {}

  login() {
    this.router.navigate(['/auth/signin']);
  }

  signin() {
    this.router.navigate(['/auth/signup']);
  }
}
