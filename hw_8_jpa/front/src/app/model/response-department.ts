import {Employee} from "./employee";
import {Department} from "./department";

export class ResponseDepartment {
  departments: Department[];
  page: number;
  totalPages: number;
  showEntity: number;
  allSizeEntity: number;
  sort: string;

  showEntityTo: number;
  showEntityFrom: number;
}
