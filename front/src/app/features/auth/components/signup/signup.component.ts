import {Component, OnInit} from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SignUpRequest} from "../../interfaces/sign-up-request";
import {BreakpointObserver, Breakpoints} from "@angular/cdk/layout";
import {UsersService} from "../../../users/services/users.service";
import {passwordValidator} from "../../../users/validators/password-validator";

const PASSWORD_VALIDATOR_PATTERN = '/^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*([^\\w\\s]|[_]))\\S{8,}$/g';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent  implements OnInit{

  onError = false;
  errorMessage = '';
  sucessMessage = "Inscription réussie";
  submitted= false;
  hideLogo = false;
  isRegistred: boolean = false;

  signUpForm = this.fb.group({
    username: [
      '',
      [
        Validators.required,
        Validators.min(8)
      ]
    ],
    email: [
      '',
      [
        Validators.required,
        Validators.email
      ]
    ],
    password: [
      '',
      [
        Validators.required,
        passwordValidator
      ]
    ]
  })

  constructor(
    private authService: AuthService,
    private userService: UsersService,
    private fb: FormBuilder,
    private router: Router,
    private responsive: BreakpointObserver
    ) {
  }

  ngOnInit(): void {
    this.responsive.observe([
      Breakpoints.XSmall,
    ]).subscribe(result => {
      this.hideLogo = true;
      if(result.matches){
        this.hideLogo = false;
      }
    })
  }

  signup() {
    const signUpRequest: SignUpRequest = this.signUpForm.value as SignUpRequest;
    console.log(signUpRequest);
    this.authService.register(signUpRequest).subscribe({
      next: value => {
        this.isRegistred = true;
        this.router.navigate(['']);
      },
      error: err => {
        this.onError = true;
        this.errorMessage = err.error.message;
      }
    });
  }

  get f(){
    return this.signUpForm.controls;
  }
}
