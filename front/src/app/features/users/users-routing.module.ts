import { NgModule } from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {MeComponent} from "./components/me/me.component";
import {FeedComponent} from "./components/feed/feed.component";

const routes: Routes = [
  {title: 'MyProfile', path: 'me', component: MeComponent},
  {title: 'MyFeed', path: 'feed', component: FeedComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UsersRoutingModule { }
