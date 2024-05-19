import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {PostComponent} from "./components/post/post.component";
import {PostListComponent} from "./components/post-list/post-list.component";
import {PostFormComponent} from "./components/post-form/post-form.component";

const routes: Routes = [
  {title: 'Create Post', path: 'create', component: PostFormComponent},
  {title: 'All Posts', path: 'all', component: PostListComponent},
  {title: 'View Post', path: ':id', component: PostComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PostsRoutingModule { }