import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { EmployeesListComponent } from './employees-list/employees-list.component';
import {HttpClientModule} from "@angular/common/http";
import {DataTablesModule} from "angular-datatables";
import {FormsModule} from "@angular/forms";
import { DepartmentsListComponent } from './department-list/departments-list.component';

@NgModule({
  declarations: [
    AppComponent,
    EmployeesListComponent,
    DepartmentsListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    DataTablesModule,
    FormsModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
