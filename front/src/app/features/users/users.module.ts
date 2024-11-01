import {NgModule} from '@angular/core';
import {MeComponent} from './components/me/me.component';
import {FeedComponent} from './components/feed/feed.component';
import {UsersRoutingModule} from "./users-routing.module";
import {CommonModule, DatePipe, NgIf} from "@angular/common";
import {PostsModule} from "../posts/posts.module";
import {ButtonModule} from "primeng/button";
import {SplitButtonModule} from "primeng/splitbutton";
import {CardModule} from "primeng/card";
import {InputTextModule} from "primeng/inputtext";
import {SplitterModule} from "primeng/splitter";
import {MessageModule} from "primeng/message";
import {ReactiveFormsModule} from "@angular/forms";
import {DividerModule} from "primeng/divider";

@NgModule({
  declarations: [
    MeComponent,
    FeedComponent,
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
        PostsModule,
        ButtonModule,
        SplitButtonModule,
        CardModule,
        InputTextModule,
        SplitterModule,
        MessageModule,
        ReactiveFormsModule,
        DividerModule,
    ]
})
export class UsersModule { }
