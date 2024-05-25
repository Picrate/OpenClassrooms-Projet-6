import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './pages/home/home.component';
import {HttpClientModule} from "@angular/common/http";
import {httpInterceptorProviders} from "./interceptors/jwtInterceptor.service";
import {HeaderComponent} from "./pages/header/header.component";
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {NgOptimizedImage} from "@angular/common";
import {ButtonModule} from "primeng/button";
import {ToolbarModule} from "primeng/toolbar";
import {DividerModule} from "primeng/divider";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MessagesModule} from "primeng/messages";
import {MessageModule} from "primeng/message";
import {ToastModule} from "primeng/toast";
import {ImageModule} from "primeng/image";
import {SidebarModule} from "primeng/sidebar";

@NgModule({
  declarations: [AppComponent, HomeComponent, HeaderComponent, NotFoundComponent],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule,
        HttpClientModule,
        NgOptimizedImage,
        ButtonModule,
        ToolbarModule,
        DividerModule,
        MessagesModule,
        MessageModule,
        ToastModule,
        ImageModule,
        SidebarModule,
    ],
  providers: [
    httpInterceptorProviders,
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  exports: [
    HeaderComponent
  ]
})
export class AppModule {}
