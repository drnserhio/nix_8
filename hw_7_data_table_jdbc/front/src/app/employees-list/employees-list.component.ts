import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {SaverToBrowserService} from "../service/saver-to-browser.service";
import {EmployeeService} from "../service/employee.service";
import {Employee} from "../model/employee";
import {Sort} from "../enum/sort-enum";
import {NgForm} from "@angular/forms";
import {ResponseEmployee} from "../model/response-employee";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Department} from "../model/department";

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
  private columnSave: string = 'id';
  private saveSort: string = 'asc';
  private updateEmployee: Employee = new Employee();

  public listSearch: Employee[] = [];
  private departmentsByEmployee: Department[];
  private saveIdEmployee: number;
  public page: number = 0;

  public free: Department[] = [];
  private selectEmployee: number;
  private departmentId: number;


  constructor(private http: HttpClient,
              private employeeService: EmployeeService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.sort(1, this.showEmployee, this.columnSave, this.saveSort);
  }

  public showEntity(value: NgForm) {
    this.saveCounterEntity = null;
    this.showEmployee = value.controls['showEntity'].value;
    if (this.saveSort == null ||
        this.saveSort == 'asc') {
      this.sort(1, this.showEmployee, this.columnSave, this.saveSort);
    } else {
      this.sort(1, this.showEmployee, this.columnSave, this.saveSort);
    }
  }

  public pagePrevious() {
    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.sort(--this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
    }  else {
      this.sort(--this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
    }
  }

  public pageNext() {

    if (this.saveSort == null ||
      this.saveSort == 'asc') {
      this.sort(++this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
    } else {
      this.sort(++this.responseEmployee.page, this.responseEmployee.showEntity, this.columnSave, this.saveSort);
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
        console.log(response);
        this.responseEmployee = response;
        this.employeeList = response.employees;
        this.saveSort = response.sort;
        if (this.saveCounterEntity == 0 ||
          this.saveCounterEntity == null) {
          this.saveCounterEntity = response.allSizeEntity;
        }
        this.saveResponseField(showSaveEntity, column, sort);
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
        document.getElementById('openModalButton').click();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  public updateEmp(employee: Employee): void {
    console.log(employee);
    this.employeeService.update(employee).subscribe(
      any => {
        alert("Update successfull");
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert("Update faild");
      }
    );
  }

  public openModal(content) {
    this.modalService.open(content, {size: 'lg'});
  }

  public close() {
    this.modalService.dismissAll();
  }

  public deleteEmployee(id: number) {
    this.employeeService.delete(id).subscribe(
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
    const res: Employee[] = [];
    for (const employee of this.employeeList) {

      if (employee.firstname.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1 ||
        employee.lastname.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1 ||
        employee.username.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1) {
        res.push(employee);
      }
    }
    this.employeeList = res;
    if (res.length === 0 ||
      !searchUsers) {
      this.sort(1, this.showEmployee, this.columnSave, this.saveSort);
    }
  }

  public findDepartmentsByEmployee(id: number) {
    this.employeeService.findDepartmentsByEmployee(id).subscribe(
      (response: Department[]) => {
        this.departmentsByEmployee = response;
        this.saveIdEmployee = id;
        document.getElementById('openModalWorkDepartment').click();
      },
      (error: HttpErrorResponse) => {
        alert("Employee don't work in all department.")
      }
    )
  }

  public deleteDepartmentInEmployee(department_id: number, employee_id: number) {
    this.employeeService.deleteDepartment(department_id, employee_id).subscribe(
      any => {
        alert("Employee went out for department successfull.")
        window.location.reload();
      },
      (error: HttpErrorResponse) => {
        alert("Employee didn't go out for department successfull.")
      }
    )
  }

  public addFreeToDepartments(id: number) {
    this.employeeService.findFreeDepartmentByEmployee(id).subscribe(
      (response: Department[]) => {
        this.free = response;
        this.selectEmployee = id;
        document.getElementById('openModalAddToDepartment').click();
      },
      (error: HttpErrorResponse) => {
        alert('Didn\'t have free department!');
      }
    )
  }

  addEmployeeToDepartment(value: NgForm) {
    this.departmentId = value.controls['showDepartment'].value;
    this.employeeService.addFreeDepartmentToEmployee(this.departmentId, this.selectEmployee).subscribe();
    this.close();
  }
}
