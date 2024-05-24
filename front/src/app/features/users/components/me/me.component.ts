import {Component, OnInit} from '@angular/core';
import {UsersService} from "../../services/users.service";
import {Router} from "@angular/router";
import {AuthService} from "../../../auth/services/auth.service";
import {SessionStorageService} from "../../../../services/session-storage.service";
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
import {UniqueUsernameOrEmailValidator} from "../../validators/unique-username-or-email-validator";
import {SessionInformation} from "../../../../interfaces/session-information";
import {Topic} from "../../../posts/interfaces/topic";
import {Observable} from "rxjs";
import {UpdateProfileRequest} from "../../interfaces/update-profile-request";
import {samePasswordValidator} from "../../validators/same-password.validator";
import {passwordValidator} from "../../validators/password-validator";

@Component({
  selector: 'app-me',
  templateUrl: './me.component.html',
  styleUrls: ['./me.component.scss']
})
export class MeComponent implements OnInit {

  private sessionsInfos!: SessionInformation;
  public topics$!: Observable<Array<Topic>>;
  public buttonLabel: string = 'Se désabonner';
  public submitted = false;
  userForm!: FormGroup;

  constructor(
    private userService: UsersService,
    private authService: AuthService,
    private sessionStorageService: SessionStorageService,
    private router : Router,
    private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.sessionsInfos = this.sessionStorageService.getUser();
    this.topics$ = this.userService.getUserTopics();

    this.userForm = this.fb.group({
      username: [
        this.sessionsInfos.username,
        [
          Validators.required,
          Validators.minLength(8),
        ],
        [
          UniqueUsernameOrEmailValidator.createValidator(this.userService),
        ]
      ],
      email: [
        this.sessionsInfos.email,
        [
          Validators.email,
          Validators.required,
        ],
        [
          UniqueUsernameOrEmailValidator.createValidator(this.userService)
        ]
      ],
      password: [
        null,
        [
          passwordValidator
        ]
      ],
      password2: [
        null
      ],
    },{
      updateOn: "blur",
      validators: samePasswordValidator
    })
  }

  get f() {return this.userForm.controls;}

  public sameUsernameValidator(): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const sameValue = this.sessionsInfos?.username !== control.value;
      return sameValue ? {sameUsername: 'true' }: null;
    };
  }

  logOut() {
    this.authService.logout().subscribe(() => {
      this.sessionStorageService.logout();
      this.router.navigate(['']);
    });
  }
  updateProfile() {
    this.submitted = true;
    if(this.userForm.touched) {
      let newUsersInfos:UpdateProfileRequest = {
        id : this.sessionsInfos.id
      };
      if(this.userForm.get('username')?.touched && this.userForm.get('username')?.valid){
        newUsersInfos.username = this.userForm.get('username')?.value;
      }
      if(this.userForm.get('email')?.touched && this.userForm.get('email')?.valid){
        newUsersInfos.email = this.userForm.get('email')?.value;
      }
      if(this.userForm.get('password')?.touched && this.userForm.get('password')?.valid){
        newUsersInfos.password = this.userForm.get('password')?.value;
      }

      this.userService.updateProfile(newUsersInfos).subscribe(response => {
        this.authService.logout();
        this.sessionStorageService.logout();
        this.router.navigate(['']);
      },error => {
        console.log(error);
      });

    }

  }
}

