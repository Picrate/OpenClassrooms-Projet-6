import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {SignInRequest} from "../../interfaces/sign-in-request";
import {FormBuilder, Validators} from "@angular/forms";
import {SessionService} from "../../../../services/session.service";
import {SessionInformation} from "../../../../interfaces/session-information";

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss'],
})

export class SigninComponent {

  public hide = true;
  public onError = false;

  signInForm = this.fb.group({
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
        Validators.min(3)
      ]
    ]
  })

  constructor(private authService: AuthService, private routeur: Router, private fb: FormBuilder, private sessionService: SessionService) {
  }

  signin() {

    const formValues = this.signInForm.value;

    if(formValues.email && formValues.password) {
      const signInRequest: SignInRequest = formValues as SignInRequest;
      this.authService.login(signInRequest).subscribe({
        next: (resp : SessionInformation) => {
          this.sessionService.logIn(resp);
          this.routeur.navigate(['/users/feed']);
        }
      });
    }
  }
}
