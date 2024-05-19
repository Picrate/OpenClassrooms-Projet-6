import {Author} from "./author";
import {Comment} from "./comment";
import {Topic} from "./topic";

export interface Post {
  id: string;
  title: string;
  content: string;
  topic: Topic;
  created_at: Date;
  author: Author;
  comments: Array<Comment>;
}
