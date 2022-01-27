import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserListComponent } from './user-list/user-list.component';
import {HttpClientModule} from "@angular/common/http";
import {RouterModule} from "@angular/router";
import {NotifierModule} from "angular-notifier";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgxPaginationModule} from "ngx-pagination";
import {NgbModule, NgbPaginationModule} from "@ng-bootstrap/ng-bootstrap";
import { SelectUserComponent } from './select-user/select-user.component';
import { CreateUserComponent } from './create-user/create-user.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    SelectUserComponent,
    CreateUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule,
    NotifierModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgbPaginationModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
