import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {DepartmentsListComponent} from "./department-list/departments-list.component";

const routes: Routes = [
  {path: "employees-list", component:EmployeesListComponent},
  {path: '', redirectTo: 'employees-list', pathMatch: 'full'},
  {path: "departments-list", component: DepartmentsListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
