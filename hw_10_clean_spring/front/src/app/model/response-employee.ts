import {Employee} from "./employee";

export class ResponseEmployee {
  employees: Employee[];
  page: number;
  totalPages: number;
  showEntity: number;
  allSizeEntity: number;
  sort: string;

  showEntityTo: number;
  showEntityFrom: number;
}
