import {Employee} from "./employee";
import {Department} from "./department";

export class ResponseDepartment {
  departments: Department[];
  page: number;
  countEntity: number;
  showEntity: number;
  allSizeEntity: number;
  sort: string;
}
