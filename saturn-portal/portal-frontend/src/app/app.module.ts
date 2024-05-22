import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ApiModule} from "../modules/generated";
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatButtonModule} from "@angular/material/button";
import {HttpClientModule, provideHttpClient, withInterceptors} from "@angular/common/http";
import {HeaderComponent} from "./auth/components/header/header.component";
import {securityInterceptor} from "./core/security-interceptor";
import {NavbarComponent} from "./shared/components/navbar/navbar.component";
import {SemesterComponent} from "./shared/components/semester/semester.component";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ApiModule,
    BrowserAnimationsModule,
    MatButtonModule,
    HttpClientModule,
    HeaderComponent,
    NavbarComponent,
    SemesterComponent,
  ],
  providers: [
    provideHttpClient(withInterceptors([
      securityInterceptor
    ]))
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
