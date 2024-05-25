import {AbstractControl, AsyncValidatorFn, ValidationErrors} from "@angular/forms";
import {catchError, map, Observable, of} from "rxjs";
import {UsersService} from "../services/users.service";
import {ResponseMessage} from "../../../interfaces/response-message";

export class UniqueUsernameOrEmailValidator {
  static createValidator(userService: UsersService): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> =>
    {
      return userService.isUsernameOrEmailAlreadyExists(control.value).pipe(
        map(
          (messageResponse :ResponseMessage) => (messageResponse.message === 'true' ? {alreadyExists: true} : null)),
        catchError(() => of(null))
      );
    }
  }
}
