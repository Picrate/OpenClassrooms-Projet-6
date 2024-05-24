import {NgModule} from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {AuthGuard} from "./guards/auth.guard";

// consider a guard combined with canLoad / canActivate route option
// to manage unauthenticated user to access private routes
const routes: Routes = [
  { path: 'auth', loadChildren: () => import ('./features/auth/auth.module').then(m => m.AuthModule)},
  { path: 'posts',canActivate:[AuthGuard],loadChildren: () => import ('./features/posts/posts.module').then(m => m.PostsModule)},
  { path: 'users',canActivate:[AuthGuard],loadChildren: () => import ('./features/users/users.module').then(m => m.UsersModule)},
  { path: '', component: HomeComponent},
  { path: '**', component: NotFoundComponent },  // Wildcard route for a 404 page
];

@NgModule({
  //imports: [RouterModule.forRoot(routes,  { enableTracing: true })],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
