import {Component, OnInit} from '@angular/core';
import {StorageService} from "./services/storage.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  title = 'front';

  public isLogged = false;

  constructor(private storageService: StorageService) {
  }

  ngOnInit(): void {
    this.isLogged = this.storageService.isLoggedIn();
  }


}
