import {Injectable} from '@angular/core';
import {throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {
  handle(error:any): void {
    console.error('An error occured', error);
    throwError('Oups something went wrong');
  }
}
