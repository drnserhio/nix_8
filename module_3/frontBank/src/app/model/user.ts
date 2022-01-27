import {Account} from "./account";

export class User {
  id: number;
  firstname: string;
  lastname: string;
  username: string;
  phone: string;
  accounts: Account[];
}
