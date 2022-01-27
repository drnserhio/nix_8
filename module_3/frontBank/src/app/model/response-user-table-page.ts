import {User} from "./user";

export class ResponseUserTablePage {
  users: User[];
  page: number;
  totalPages: number
  showEntity: number;
  allSizeEntity: number;
  sort: string;
  showEntityTo: number
  showEntityFrom: number;
}
