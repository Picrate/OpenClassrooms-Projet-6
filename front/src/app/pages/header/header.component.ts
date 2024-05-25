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
  display: boolean = false;

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
    if(this.display){
      this.display = false;
    }
    this.router.navigate(['/users/feed']);
  }

  goTopics() {
    if(this.display){
      this.display = false;
    }
    this.router.navigate(['/posts/topics']);
  }

  myProfile() {
    if(this.display){
      this.display = false;
    }
    this.router.navigate(['/users/me']);
  }

}
