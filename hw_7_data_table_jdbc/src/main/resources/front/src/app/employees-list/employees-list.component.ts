import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {SaverToBrowserService} from "../service/saver-to-browser.service";
import {EmployeeService} from "../service/employee.service";
import {Employee} from "../model/employee";
import {Sort} from "../enum/sort-enum";
import {NgForm} from "@angular/forms";
import {ResponseEmployee} from "../model/response-employee";

@Component({
  selector: 'app-employees-list',
  templateUrl: './employees-list.component.html',
  styleUrls: ['./employees-list.component.css']
})
export class EmployeesListComponent implements OnInit {

  public responseEmployee: ResponseEmployee;
  public ListValueOption: number[] = [10, 25, 50, 100];
  public showEmployee: number = 10;
  private employeeList: Employee[];
  private saveCounterEntity: number;
  private flag: boolean;
  private columnSave: string;
  private saveSort: string = 'asc';
  private updateEmployee: Employee;


  constructor(private http: HttpClient,
              private employeeService: EmployeeService) {
  }

  ngOnInit() {
    this.limitList(1, this.showEmployee);
  }

  public findAllBase() {
    this.employeeService.findAllBase().subscribe(
      (list: Employee[]) => {
        this.employeeList = list;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public limitList(page: number, showEntity: number) {
    this.employeeService.limitList(page, showEntity).subscribe(
      (response: ResponseEmployee) => {
        this.responseEmployee = response;
        this.employeeList = response.employees;
        if (this.saveCounterEntity == 0 ||
              this.saveCounterEntity == null) {
          this.saveCounterEntity = response.allSizeEntity;
        }
        this.isBoundEntity();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }


  public showEntity(value: NgForm) {
    this.saveCounterEntity = null;
    this.showEmployee = value.controls['showEntity'].value;
    if (this.saveSort == null ||
        this.saveSort == 'asc') {
      this.limitList(1, this.showEmployee);
    } else {
      this.sort(1, this.showEmployee, this.columnSave, this.saveSort);
    }
  }

  public pagePrevious() {
    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.limitList(--this.responseEmployee.page, this.showEmployee);
    }  else {
      this.sort(--this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
    }
  }

  public pageNext() {

    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.limitList(++this.responseEmployee.page, this.showEmployee);
    } else {
      this.sort(++this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
    }
  }

  public isBoundEntity() {
    this.saveCounterEntity =  this.saveCounterEntity - this.responseEmployee.showEntity ;
    console.log(this.saveCounterEntity);


    if (this.saveCounterEntity >= 0) {
      this.flag = false;
    } else {
      this.flag = true;
    }
  }


  public saveResponseField(showSaveEntity: number, column: string, sort: string) {
    this.columnSave = column;
    this.showEmployee = showSaveEntity;
    this.saveSort = sort;

  }

  public sort(page: number, showSaveEntity: number, column: string, sort: string) {

    this.employeeService.listWithSortColumn(page, showSaveEntity, column, sort).subscribe(
      (response: ResponseEmployee) => {
        this.responseEmployee = response;
        this.employeeList = response.employees;
        this.saveSort = response.sort;
        if (this.saveCounterEntity == 0 ||
          this.saveCounterEntity == null) {
          this.saveCounterEntity = response.allSizeEntity;
        }
        this.saveResponseField(showSaveEntity, column, sort);
        this.isBoundEntity();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    )
  }

  public sortById() {
    this.saveCounterEntity = null;
    if (this.saveSort == 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showEmployee, 'id', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showEmployee, 'id', this.saveSort);
    }

  }

  public sortByDate(date: string) {
    this.saveCounterEntity = null;
    if (this.saveSort == 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showEmployee, date, this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showEmployee, date, this.saveSort);
    }

  }

  public sortByFirstname() {
    this.saveCounterEntity = null;
    if (this.saveSort == "asc") {
      this.saveSort = 'desc';
      this.sort(1, this.showEmployee, 'firstname', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showEmployee, 'firstname', this.saveSort);
    }
  }

  public sortByLastname() {
    this.saveCounterEntity = null;
    if (this.saveSort == "asc") {
      this.saveSort = 'desc';
      this.sort(1, this.showEmployee, 'lastname', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showEmployee, 'lastname', this.saveSort);
    }
  }

  public sortByUsername() {
    if (this.saveSort == "asc") {
      this.saveSort = 'desc';
      this.sort(1, this.showEmployee, 'username', this.saveSort);
    }
    else {
      this.saveSort = 'asc';
      this.sort(1, this.showEmployee, 'username', this.saveSort);
    }

  }

  public chooseEmployee(id: number) {
    this.employeeService.get(id).subscribe(
      (response: Employee) => {
        this.updateEmployee = response;
        document.getElementById('exampleModal');

      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public updateEmp(employee: Employee): void {
    console.log(employee);
    this.employeeService.update(employee).subscribe(
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }
}
