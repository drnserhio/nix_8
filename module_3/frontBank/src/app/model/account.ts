import {Operation} from "./operation";

export class Account {
  id: number;
  nameAccount: string;
  money: number;
  createAccount: Date;
  operation: Operation[];
}
