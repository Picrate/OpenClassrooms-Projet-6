export interface User {
  id: string;
  email: string;
  username: string;
  topics: Array<string>;
  created_at: Date;
  updated_at?: Date;
}
