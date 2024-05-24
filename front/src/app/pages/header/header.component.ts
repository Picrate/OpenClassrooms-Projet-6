import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router, UrlSegment} from "@angular/router";
import {Observable} from "rxjs";
import {SessionStorageService} from "../../services/session-storage.service";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  isLoggedIn$ ?: Observable<boolean>;
  xs = false;

  constructor(
    private storageService: SessionStorageService,
    protected router: Router,
    private responsive: BreakpointObserver,
    private activatedRoute : ActivatedRoute
    ) { }

  ngOnInit(): void {
    this.isLoggedIn$ = this.storageService.isLoggedIn();
    this.responsive.observe([
      Breakpoints.XSmall,
    ]).subscribe(result => {
      this.xs = result.matches;
    })
  }

  goToHome() {
    this.router.navigate(['/users/feed']);
  }

  myProfile() {
    this.router.navigate(['/users/me']);
  }
}
