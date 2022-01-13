import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "../model/employee";
import {ResponseEmployee} from "../model/response-employee";
import {Sort} from "../enum/sort-enum";
import {Department} from "../model/department";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  host = environment.backUrl;
  constructor(private http: HttpClient) { }

  public create(employee: Employee): Observable<void | HttpErrorResponse> {
    return this.http.post<void | HttpErrorResponse>(`${this.host}/employee/create`, employee);
  }

  public update(employee: Employee): Observable<void | HttpErrorResponse> {
    return this.http.put<void | HttpErrorResponse>(`${this.host}/employee/update`, employee);
  }

  public delete(id: number): Observable<void | HttpErrorResponse>  {
    return this.http.delete<void | HttpErrorResponse>(`${this.host}/employee/del/${id}`);
  }

  public get(id: number): Observable<Employee | HttpErrorResponse> {
    return this.http.get<Employee | HttpErrorResponse>(`${this.host}/employee/get/${id}`);
  }

  public findAllBase(): Observable<Employee[] | HttpErrorResponse> {
    return this.http.get<Employee[] | HttpErrorResponse>(`${this.host}/employee/list-employee`)
  }

  public limitList(page: number, showEntity: number): Observable<ResponseEmployee | HttpErrorResponse> {
    return this.http.get<ResponseEmployee | HttpErrorResponse>(`${this.host}/employee/limit-list/${page}/${showEntity}`);
  }

  public listWithSortColumn(page: number, showEntity: number,column: string, sort: string): Observable<ResponseEmployee | HttpErrorResponse> {
   return this.http.get<ResponseEmployee | HttpErrorResponse>(`${this.host}/employee/limit-list/${page}/${showEntity}/${column}/${sort}`);
  }


  public findDepartmentsByEmployee(id: number):Observable<Department[] | HttpErrorResponse> {
    return this.http.get<Department[] | HttpErrorResponse>(`${this.host}/employee/get/all-departments/${id}`);
  }

  public deleteDepartment(department_id: number, employee_id: number): Observable<void | HttpErrorResponse> {
    return this.http.delete<void | HttpErrorResponse>(`${this.host}/employee/del/${department_id}/${employee_id}`);
  }
}
