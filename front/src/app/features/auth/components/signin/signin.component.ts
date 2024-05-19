import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SignInRequest} from "../../interfaces/sign-in-request";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from "../../../../services/session-storage.service";

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
    private storageService: SessionStorageService,
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
    this.authService.login(signinRequest).subscribe({
      next: value => {
        this.storageService.saveUser(value);
        this.routeur.navigate(['/users/feed']);
      },
      error: err => {
        this.isLoginFailed = true;
        this.errorMessage = err.error.message;
        }
    });

  }
}
