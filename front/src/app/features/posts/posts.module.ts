import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostComponent } from './components/post/post.component';
import { PostListComponent } from './components/post-list/post-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PostsRoutingModule} from "./posts-routing.module";
import { PostFormComponent } from './components/post-form/post-form.component';
import {InputTextareaModule} from "primeng/inputtextarea";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";

@NgModule({
    declarations: [
        PostComponent,
        PostListComponent,
        PostFormComponent
    ],
  exports: [
    PostComponent,
    PostListComponent,
    PostFormComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextareaModule,
    ButtonModule,
    InputTextModule
  ]
})
export class PostsModule { }
