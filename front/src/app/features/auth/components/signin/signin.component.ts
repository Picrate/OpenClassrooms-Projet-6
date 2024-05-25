import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SignInRequest} from "../../interfaces/sign-in-request";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {SessionStorageService} from "../../../../services/session-storage.service";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss'],
})
export class SigninComponent implements OnInit{

  hideLogo: boolean = false;

  signinForm = new FormGroup(
    {
      login: new FormControl('', [Validators.required]),
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
    private router: Router,
    private responsive: BreakpointObserver
    ) {

  }

  ngOnInit(): void {

    this.responsive.observe([
      Breakpoints.XSmall,
    ]).subscribe(result => {
      this.hideLogo = !result.matches;
    })

    if(this.storageService.isLoggedIn()){
      this.isLogged = true;
      this.roles = this.storageService.getUser()?.roles;
    }
  }

  get f(){
    return this.signinForm.controls;
  }

  onSubmit(){
    const signinRequest = this.signinForm.value as SignInRequest;
    this.authService.login(signinRequest).subscribe({
      next: value => {
        this.storageService.saveUser(value);
        this.router.navigate(['/users/feed']);
      },
      error: err => {
        this.isLoginFailed = true;
        this.errorMessage = err.error.message;
        }
    });

  }
}
