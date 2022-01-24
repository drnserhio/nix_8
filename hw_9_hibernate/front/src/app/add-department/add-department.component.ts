import { Component, OnInit } from '@angular/core';
import {DepartmentService} from "../service/department.service";
import {Department} from "../model/department";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add-department',
  templateUrl: './add-department.component.html',
  styleUrls: ['./add-department.component.css']
})
export class AddDepartmentComponent implements OnInit {

  constructor(private departmentService: DepartmentService,
              private router: Router) { }

  ngOnInit() {
  }

  public create(department: Department) {
    this.departmentService.create(department).subscribe(
      any => {
        alert("Department created successfull!");
        this.router.navigate([`/departments-list`]);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert("Department create failed")
      }
    )
  }

  toHome() {
    this.router.navigateByUrl(`/employees-list`);
  }
}
