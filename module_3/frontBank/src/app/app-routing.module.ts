import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {SelectUserComponent} from "./select-user/select-user.component";

const routes: Routes = [
  {path: 'user/home', component: UserListComponent},
  {path: '', redirectTo: 'user/home', pathMatch: "full"},
  {path: "select_user", component: SelectUserComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
