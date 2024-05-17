import {User} from "../features/users/interfaces/user";

export interface SessionInformation {
  token: string;
  type: string;
  user: User
}
