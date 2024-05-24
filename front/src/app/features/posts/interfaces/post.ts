import {Author} from "./author";
import {Comment} from "./comment";
import {Topic} from "./topic";

export interface Post {
  id: string;
  title: string;
  content: string;
  topic: Topic;
  createdAt: Date;
  author: Author;
  comments: Array<Comment>;
}
