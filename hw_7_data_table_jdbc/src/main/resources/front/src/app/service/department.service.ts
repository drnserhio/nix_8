import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {ResponseEmployee} from "../model/response-employee";
import {ResponseDepartment} from "../model/response-department";

@Injectable({
  providedIn: 'root'
})
export class DepartmentService {
  host = environment.backUrl;
  constructor(private http: HttpClient) { }

  public limitList(page: number, showEntity: number): Observable<ResponseDepartment | HttpErrorResponse> {
    return this.http.get<ResponseDepartment | HttpErrorResponse>(`${this.host}/department/limit-list/${page}/${showEntity}`);
  }

  public listWithSortColumn(page: number, showEntity: number,column: string, sort: string): Observable<ResponseDepartment | HttpErrorResponse> {
    return this.http.get<ResponseDepartment | HttpErrorResponse>(`${this.host}/department/limit-list/${page}/${showEntity}/${column}/${sort}`);
  }
}
