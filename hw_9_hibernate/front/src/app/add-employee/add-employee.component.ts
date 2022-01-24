import { Component, OnInit } from '@angular/core';
import {EmployeeService} from "../service/employee.service";
import {Employee} from "../model/employee";
import {Router} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-add-employee',
  templateUrl: './add-employee.component.html',
  styleUrls: ['./add-employee.component.css']
})
export class AddEmployeeComponent implements OnInit {

  constructor(private employeeService: EmployeeService,
              private router: Router) { }

  ngOnInit() {
  }

  public create(employee: Employee) {
    console.log(employee);
    this.employeeService.create(employee).subscribe(
      any => {
        alert("Employee created successfull!");
        this.router.navigate([`/`]);
      },
      (error: HttpErrorResponse) => {
        alert("Employee didn't create successfull!" +
          "Change your field... ;)")
      }
    );
  }

  toHome() {
    this.router.navigateByUrl(`/employees-list`);
  }
}
