import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import {DepartmentsListComponent} from "./department-list/departments-list.component";
import {AddEmployeeComponent} from "./add-employee/add-employee.component";
import {AddDepartmentComponent} from "./add-department/add-department.component";

const routes: Routes = [
  {path: "employees-list", component:EmployeesListComponent},
  {path: '', redirectTo: 'employees-list', pathMatch: 'full'},
  {path: "departments-list", component: DepartmentsListComponent},
  {path: "add-employee", component: AddEmployeeComponent},
  {path: "add-department", component: AddDepartmentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
