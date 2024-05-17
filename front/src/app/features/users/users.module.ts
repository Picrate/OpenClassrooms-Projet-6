import { NgModule } from '@angular/core';
import { MeComponent } from './components/me/me.component';
import { FeedComponent } from './components/feed/feed.component';
import {UsersRoutingModule} from "./users-routing.module";
import {CommonModule, DatePipe, NgIf} from "@angular/common";
import {MatCardModule} from "@angular/material/card";
import {MatIconModule} from "@angular/material/icon";
import {BrowserModule} from "@angular/platform-browser";
import {PostsModule} from "../posts/posts.module";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";

@NgModule({
  declarations: [
    MeComponent,
    FeedComponent
  ],
  exports: [
    MeComponent,
    FeedComponent
  ],
  imports: [
    CommonModule,
    UsersRoutingModule,
    DatePipe,
    NgIf,
    MatCardModule,
    MatIconModule,
    PostsModule,
    MatButtonModule,
    MatMenuModule,
    MatFormFieldModule,
    MatInputModule
  ]
})
export class UsersModule { }
