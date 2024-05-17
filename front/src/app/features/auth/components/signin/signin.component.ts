import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SignInRequest} from "../../interfaces/sign-in-request";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {SessionService} from "../../../../services/session.service";
import {SessionInformation} from "../../../../interfaces/session-information";
import {StorageService} from "../../../../services/storage.service";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss'],
})
export class SigninComponent implements OnInit{

  signinForm = new FormGroup(
    {
      email: new FormControl('', [Validators.email, Validators.required]),
      password: new FormControl('', [Validators.required])
    }
  );

  isLogged = false;
  isLoginFailed = false;
  errorMessage ='';
  roles: string[] | undefined = [];

  constructor(
    private authService: AuthService,
    private storageService: StorageService,
    private routeur: Router,
    ) {}

  ngOnInit(): void {
    if(this.storageService.isLoggedIn())
      this.isLogged = true;
      this.roles = this.storageService.getUser()?.roles;
  }

  getEmail(){
    return this.signinForm.get('email');
  }


  onSubmit(){
    const signinRequest = this.signinForm.value as SignInRequest;
    this.authService.login(signinRequest).subscribe(
      (response) => {
        this.isLogged = true;
        this.storageService.saveUser(response)
        this.routeur.navigate(['/users/feed']);
      },
      error => {
        this.isLoginFailed = true;
        this.errorMessage = error;
        console.log(this.errorMessage);
      }
    );

  }
}
