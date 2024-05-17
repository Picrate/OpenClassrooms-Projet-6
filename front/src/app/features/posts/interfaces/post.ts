import {Author} from "./author";
import {Comment} from "./comment";

export interface Post {
  id: string;
  title: string;
  content: string;
  topic: string;
  created_at: Date;
  author: Author;
  comments: Array<Comment>;
}
