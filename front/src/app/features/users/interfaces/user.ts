import {Topic} from "../../posts/interfaces/topic";

export interface User {
  id: string;
  email: string;
  username: string;
  topics: Array<Topic>;
  created_at: Date;
  updated_at?: Date;
}
