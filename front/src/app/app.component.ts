import {Component, OnInit} from '@angular/core';
import {Subscription} from "rxjs";
import {SessionStorageService} from "./services/session-storage.service";
import {AuthService} from "./features/auth/services/auth.service";
import {EventBusService} from "./services/event-bus.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{

  eventBusSub?: Subscription;

  title = 'front';
  constructor(
    private storageService: SessionStorageService,
    private authService: AuthService,
    private eventBusService: EventBusService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.eventBusSub = this.eventBusService.on('logout', ()=> {
      this.logout();
    });
    if(this.storageService.hasRefreshTokenOnLocalStorage()){
      const refreshToken = this.storageService.getRefreshToken();
      if(refreshToken.refreshToken !== null && refreshToken.refreshToken.length>0){
        this.authService.refreshToken(refreshToken).subscribe(response => {
          this.storageService.saveUser(response);
            this.router.navigate(['/users/feed']);
        })
      }
    }

  }

  private logout() {
    this.authService.logout().subscribe({
      next: res => {
        this.storageService.logout();
        window.location.reload();
      },
      error:err => {
        console.log(err);
      }
    });
  }
}
