import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SessionService} from "../../../../services/session.service";
import {SignInRequest} from "../../interfaces/sign-in-request";
import {SessionInformation} from "../../../../interfaces/session-information";
import {SignUpRequest} from "../../interfaces/sign-up-request";

const PASSWORD_VALIDATOR_PATTERN = '/^(?=\\S*[a-z])(?=\\S*[A-Z])(?=\\S*\\d)(?=\\S*([^\\w\\s]|[_]))\\S{8,}$/g';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  public hide = true;
  public onError = false;

  signUpForm = this.fb.group({
    username: [
      '',
      [
        Validators.required,
        Validators.min(3)
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
        Validators.min(8),
        Validators.pattern(PASSWORD_VALIDATOR_PATTERN)
      ]
    ]
  })

  constructor(private authService: AuthService, private routeur: Router, private fb: FormBuilder, private sessionService: SessionService) {
  }

  signup() {
    const signUpRequest: SignUpRequest = this.signUpForm.value as SignUpRequest;
    this.authService.register(signUpRequest).subscribe((response) =>  {



    })  }
}
