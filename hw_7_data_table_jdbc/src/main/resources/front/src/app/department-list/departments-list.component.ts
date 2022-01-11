import { Component, OnInit } from '@angular/core';
import {ResponseEmployee} from "../model/response-employee";
import {Employee} from "../model/employee";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {ResponseDepartment} from "../model/response-department";
import {Department} from "../model/department";
import {EmployeeService} from "../service/employee.service";
import {DepartmentService} from "../service/department.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

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
  private updateDepartment: Department = new Department();

  public listSearch: Department[] = [];
  private employeesByDepartment: Employee[];
  private saveIdDepartment: number;

  constructor(private departmentService: DepartmentService,
              private modalService: NgbModal) {
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


  public chooseDepartment(id: number) {
    this.departmentService.get(id).subscribe(
      (response: Department) => {
        this.updateDepartment = response;
        console.log(response);
        document.getElementById('openModalButton').click();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public updateEmp(department: Department): void {
    this.departmentService.update(department).subscribe(
      any => {
        alert("Update successfull");
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert("Update didn't work, check your fields");
        console.log(error.message);
      }
    );
  }

  public openModal(content) {
    this.modalService.open(content, {size: 'lg'});
  }

  public close() {
    this.modalService.dismissAll();
  }

  public deleteDepartment(id: number) {
    this.departmentService.delete(id).subscribe(
      any => {
        alert("Delete successfull");
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert("Delete faild");
      }
    );
  }

  public search(searchUsers: string): void {
    const res: Department[] = [];
    for (const department of this.departmentList) {
      if (department.nameCompany.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1 ||
        department.address.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1) {
        res.push(department);
      }
    }
    this.departmentList = res;
    if (res.length === 0 ||
      !searchUsers) {
      this.limitList(1, this.showDepartment);
    }
  }

  public findAllEmployeeByDepartment(id: number) {
    this.departmentService.findAllEmployeeByDepartment(id).subscribe(
      (response: Employee[]) => {
        this.employeesByDepartment = response;
        this.saveIdDepartment = id;
        document.getElementById('openModalWorkEmployee').click();
      },
      (error: HttpErrorResponse) => {
        alert("Department don't have employees.")
      }
    )
  }

  public deleteEmployeeFromDeparment(department_id: number, employee_id: number) {
    this.departmentService.deleteEmployeeFromDeparment(department_id, employee_id).subscribe(
      any => {
        alert("Employee delete for department successfull.")
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert("Employee didn't delete for department successfull.")
      }
    )
  }


}
