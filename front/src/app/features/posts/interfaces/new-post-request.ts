import {Topic} from "./topic";

export interface NewPostRequest {
  topic: Topic | undefined;
  title: string | undefined | null;
  content: string | undefined | null;
}
