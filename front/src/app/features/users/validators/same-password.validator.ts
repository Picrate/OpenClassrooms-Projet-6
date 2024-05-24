import {AbstractControl, ValidationErrors, ValidatorFn} from "@angular/forms";

export const samePasswordValidator: ValidatorFn = (control: AbstractControl):
  ValidationErrors | null => {
  const password = control.get('password')?.value;
  const password2 = control.get('password2')?.value;
  return password !== password2 ? {passwordDiffers: true} : null;
};



