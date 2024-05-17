import {Topic} from "./topic";
import {User} from "../../users/interfaces/user";

export interface NewPostRequest {
  topic: Topic;
  title: string;
  content: string;
  author: User;
  createdAt: Date;
}
