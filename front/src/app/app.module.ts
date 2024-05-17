import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {BrowserModule} from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from "./interceptors/jwtInterceptor.service";
import {HeaderComponent} from "./pages/header/header.component";
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {MatListModule} from "@angular/material/list";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";

@NgModule({
  declarations: [AppComponent, HomeComponent, HeaderComponent, NotFoundComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    MatListModule,
    MatIconModule,
    MatMenuModule,
  ],
  providers: [httpInterceptorProviders],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule {}
