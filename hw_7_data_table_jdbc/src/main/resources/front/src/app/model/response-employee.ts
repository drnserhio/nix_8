import {Employee} from "./employee";

export class ResponseEmployee {
  employees: Employee[];
  page: number;
  countEntity: number;
  showEntity: number;
  allSizeEntity: number;
  sort: string;
}
