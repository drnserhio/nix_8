import { Component, OnInit } from '@angular/core';
import {ResponseEmployee} from '../model/response-employee';
import {Employee} from '../model/employee';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {NgForm} from '@angular/forms';
import {ResponseDepartment} from '../model/response-department';
import {Department} from '../model/department';
import {EmployeeService} from '../service/employee.service';
import {DepartmentService} from '../service/department.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-department-list',
  templateUrl: './departments-list.component.html',
  styleUrls: ['./departments-list.component.css']
})
export class DepartmentsListComponent implements OnInit {

  public responseDepartment: ResponseDepartment;
  public ListValueOption: number[] = [10, 25, 50, 100];
  public showDepartment = 10;
  private departmentList: Department[];
  private saveCounterEntity: number;
  private flag: boolean;
  private columnSave: string = 'id';
  private saveSort = 'asc';
  private updateDepartment: Department = new Department();

  public listSearch: Department[] = [];
  private employeesByDepartment: Employee[];
  private saveIdDepartment: number;

  public listEmployeeForAddDepartment: number[] = [];

  constructor(private departmentService: DepartmentService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.sort(1, this.showDepartment, this.columnSave, this.saveSort);
  }


  public showEntity(value: NgForm) {
    this.saveCounterEntity = null;
    this.showDepartment = value.controls.showEntity.value;
    if (this.saveSort == null ||
      this.saveSort === 'asc') {
      this.sort(1, this.showDepartment, this.columnSave, this.saveSort);
    } else {
      this.sort(1, this.showDepartment, this.columnSave, this.saveSort);
    }
  }

  public pagePrevious() {
    if (this.saveSort == null ||
      this.saveSort === 'asc') {
      this.sort(--this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
    }  else {
      this.sort(--this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
    }
  }

  public pageNext() {

    if (this.saveSort == null ||
      this.saveSort === 'asc') {
      this.sort(++this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
    } else {
      this.sort(++this.responseDepartment.page, this.responseDepartment.showEntity, this.columnSave, this.saveSort);
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
        if (this.saveCounterEntity === 0 ||
          this.saveCounterEntity == null) {
          this.saveCounterEntity = response.allSizeEntity;
        }
        this.saveResponseField(showSaveEntity, column, sort);
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public sortById() {
    this.saveCounterEntity = null;
    if (this.saveSort === 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, 'id', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, 'id', this.saveSort);
    }

  }

  public sortByDate(date: string) {
    this.saveCounterEntity = null;
    if (this.saveSort === 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, date, this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, date, this.saveSort);
    }

  }

  public sortByNameCompany() {
    this.saveCounterEntity = null;
    if (this.saveSort === 'asc') {
      this.saveSort = 'desc';
      this.sort(1, this.showDepartment, 'nameCompany', this.saveSort);
    } else {
      this.saveSort = 'asc';
      this.sort(1, this.showDepartment, 'nameCompany', this.saveSort);
    }
  }

  public sortByAddress() {
    this.saveCounterEntity = null;
    if (this.saveSort === 'asc') {
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
        alert('Update successfull');
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert('Update didn\'t work, check your fields');
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
        alert('Delete successfull');
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert('Delete faild');
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
      this.sort(1, this.showDepartment, this.columnSave, this.saveSort);
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
        alert('Department don\'t have employees.');
      }
    );
  }

  public deleteEmployeeFromDeparment(departmentId: number, employeeId: number) {
    this.departmentService.deleteEmployeeFromDeparment(departmentId, employeeId).subscribe(
      any => {
        alert('Employee delete for department successfull.');
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert('Employee didn\'t delete for department successfull.');
      }
    );
  }


  public addEmployeesToDepartment() {
    // tslint:disable-next-line:prefer-for-of
    let i = 0;
    for (i = 0; i < this.listEmployeeForAddDepartment.length; i++) {
      console.log(this.saveIdDepartment);
      this.departmentService.addEmployeesToDepartment(this.saveIdDepartment, this.listEmployeeForAddDepartment[i]).subscribe(
        any => {
          alert('Employees add to department succesfull.');
        }
      );
      if (i === this.listEmployeeForAddDepartment.length - 1) {
        window.location.reload();
      }
    }

  }

  public addToModel(departmentId: number) {
    this.departmentService.findAllEmployees(departmentId).subscribe(
      (response: Employee[] ) => {
        console.log(departmentId);
        this.employeesByDepartment = response;
        this.saveIdDepartment = departmentId;
        console.log(response);
        document.getElementById('openModalADDEmployee').click();
      },
      (error: HttpErrorResponse) => {
        alert('Department don\'t have employees.');
      }
    );
  }

  public saveToArrayEmployee(employeeId: number) {
    this.listEmployeeForAddDepartment.push(employeeId);

  }
}
