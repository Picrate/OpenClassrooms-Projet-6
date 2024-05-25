import {SessionInformation} from "./session-information";

export interface JwtResponse {
  type: string,
  refreshToken: string,
  user: SessionInformation
}
