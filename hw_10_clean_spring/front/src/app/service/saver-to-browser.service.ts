import {Injectable} from '@angular/core';
import {Employee} from "../model/employee";

@Injectable({
  providedIn: 'root'
})
export class SaverToBrowserService {

  constructor() { }


  public saveListEmployeesToLocalCache(employees: Employee[]) : void {
    localStorage.setItem('employees', JSON.stringify(employees));
  }

  public getEmployeesList(): Employee[] {
    return JSON.parse(localStorage.getItem('employees'));
  }

}
