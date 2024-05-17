import {Author} from "./author";

export interface Comment {
  content: string;
  created_at: Date;
  author: Author;
}
