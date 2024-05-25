import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {PostComponent} from './components/post/post.component';
import {PostListComponent} from './components/post-list/post-list.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PostsRoutingModule} from "./posts-routing.module";
import {PostFormComponent} from './components/post-form/post-form.component';
import {InputTextareaModule} from "primeng/inputtextarea";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {TopicListComponent} from './components/topic-list/topic-list.component';
import {CardModule} from "primeng/card";
import {TopicCardComponent} from './components/topic-card/topic-card.component';
import {PostCardComponent} from './components/post-card/post-card.component';
import {AutoCompleteModule} from "primeng/autocomplete";
import {DividerModule} from "primeng/divider";
import {MessageModule} from "primeng/message";

@NgModule({
    declarations: [
        PostComponent,
        PostListComponent,
        PostFormComponent,
        TopicListComponent,
        TopicCardComponent,
        PostCardComponent
    ],
  exports: [
    PostComponent,
    PostListComponent,
    PostFormComponent,
    TopicCardComponent
  ],
  imports: [
    CommonModule,
    PostsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    InputTextareaModule,
    ButtonModule,
    InputTextModule,
    CardModule,
    AutoCompleteModule,
    DividerModule,
    MessageModule
  ]
})
export class PostsModule { }
