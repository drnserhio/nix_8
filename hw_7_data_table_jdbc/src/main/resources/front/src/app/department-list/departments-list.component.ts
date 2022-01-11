import { Component, OnInit } from '@angular/core';
import {ResponseEmployee} from "../model/response-employee";
import {Employee} from "../model/employee";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {ResponseDepartment} from "../model/response-department";
import {Department} from "../model/department";
import {EmployeeService} from "../service/employee.service";
import {DepartmentService} from "../service/department.service";

@Component({
  selector: 'app-department-list',
  templateUrl: './departments-list.component.html',
  styleUrls: ['./departments-list.component.css']
})
export class DepartmentsListComponent implements OnInit {

  public responseDepartment: ResponseDepartment;
  public ListValueOption: number[] = [10, 25, 50, 100];
  public showDepartment: number = 10;
  private departmentList: Department[];
  private saveCounterEntity: number;
  private flag: boolean;
  private columnSave: string;
  private saveSort: string = 'asc';

  constructor(private http: HttpClient,
              private departmentService: DepartmentService) {
  }

  ngOnInit() {
    this.limitList(1, this.showDepartment);
  }


  public limitList(page: number, showEntity: number) {
    this.departmentService.limitList(page, showEntity).subscribe(
      (response: ResponseDepartment) => {
        this.responseDepartment = response;
        this.departmentList = response.departments;
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
    this.showDepartment = value.controls['showEntity'].value;
    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.limitList(1, this.showDepartment);
    } else {
      this.sort(1, this.showDepartment, this.columnSave, this.saveSort);
    }
  }

  public pagePrevious() {
    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.limitList(--this.responseDepartment.page, this.showDepartment);
    }  else {
      this.sort(--this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
    }
  }

  public pageNext() {

    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.limitList(++this.responseDepartment.page, this.showDepartment);
    } else {
      this.sort(++this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
    }
  }

  public isBoundEntity() {
    this.saveCounterEntity =  this.saveCounterEntity - this.responseDepartment.showEntity ;
    console.log(this.saveCounterEntity);


    if (this.saveCounterEntity >= 0) {
      this.flag = false;
    } else {
      this.flag = true;
    }
  }


  public saveResponseField(showSaveEntity: number, column: string, sort: string) {
    this.columnSave = column;
    this.showDepartment = showSaveEntity;
    this.saveSort = sort;

  }

  public sort(page: number, showSaveEntity: number, column: string, sort: string) {

    this.departmentService.listWithSortColumn(page, showSaveEntity, column, sort).subscribe(
      (response: ResponseDepartment) => {
        this.responseDepartment = response;
        this.departmentList = response.departments;
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
      this.sort(1, this.showDepartment, 'id', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, 'id', this.saveSort);
    }

  }

  public sortByDate(date: string) {
    this.saveCounterEntity = null;
    if (this.saveSort == 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, date, this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, date, this.saveSort);
    }

  }

  public sortByNameCompany() {
    this.saveCounterEntity = null;
    if (this.saveSort == "asc") {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, 'nameCompany', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, 'nameCompany', this.saveSort);
    }
  }

  public sortByAddress() {
    this.saveCounterEntity = null;
    if (this.saveSort == "asc") {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, 'address', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, 'address', this.saveSort);
    }
  }

}
