import { Injectable } from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponseEmployee} from '../model/response-employee';
import {ResponseDepartment} from '../model/response-department';
import {Department} from '../model/department';
import {Employee} from '../model/employee';

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  host = environment.backUrl;
  constructor(private http: HttpClient) { }

  public limitList(page: number, showEntity: number): Observable<ResponseDepartment | HttpErrorResponse> {
    return this.http.get<ResponseDepartment | HttpErrorResponse>(`${this.host}/department/limit-list/${page}/${showEntity}`);
  }

  public listWithSortColumn(page: number, showEntity: number, column: string, sort: string): Observable<ResponseDepartment | HttpErrorResponse> {
    return this.http.get<ResponseDepartment | HttpErrorResponse>(`${this.host}/department/limit-list/${page}/${showEntity}/${column}/${sort}`);
  }

  public create(department: Department): Observable<void | HttpErrorResponse>  {
    return this.http.post<void | HttpErrorResponse>(`${this.host}/department/create`, department);
  }
  public update(department: Department): Observable<void | HttpErrorResponse> {
    return this.http.put<void | HttpErrorResponse>(`${this.host}/department/update`, department);
  }

  public delete(id: number): Observable<void | HttpErrorResponse> {
    return this.http.delete<void | HttpErrorResponse>(`${this.host}/department/del/${id}`);
  }

  public get(id: number): Observable<Department | HttpErrorResponse> {
    return this.http.get<Department | HttpErrorResponse>(`${this.host}/department/get/${id}`);
  }

  public findAllEmployeeByDepartment(id: number): Observable<Employee[] | HttpErrorResponse> {
    return this.http.get<Employee[] | HttpErrorResponse>(`${this.host}/department/get/all-employees/${id}`);
  }

  public deleteEmployeeFromDeparment(departmentId: number, employeeId: number): Observable<void | HttpErrorResponse> {
    return this.http.delete<void | HttpErrorResponse>(`${this.host}/department/del/${departmentId}/${employeeId}`);
  }

  public addEmployeesToDepartment(departmentId: number, employeeId: number): Observable<void | HttpErrorResponse> {
    return this.http.get<void | HttpErrorResponse>(`${this.host}/department/add/${departmentId}/${employeeId}`);
  }

  public findAllEmployees(departmentId: number): Observable<Employee[] | HttpErrorResponse> {
    return this.http.get<Employee[] | HttpErrorResponse>(`${this.host}/department/list-employees/${departmentId}`);
  }
}
