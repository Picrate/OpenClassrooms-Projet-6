import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export const passwordValidator : ValidatorFn = (control: AbstractControl):
    ValidationErrors | null => {
    const hasNumber = /\d/.test(control.value);
    const hasUpper = /[A-Z]/.test(control.value);
    const hasLower = /[a-z]/.test(control.value);
    const hasSpecialCharacter = /[-!$%^&*()_+|~=`{}[:;<>?,.@#\]]/.test(control.value);
    const hasMinEightChars = /.{8,}/.test(control.value);
    const valid = hasNumber && hasLower && hasUpper && hasSpecialCharacter && hasMinEightChars;
    return !valid ? {invalidPassword: true} : null;
  };

