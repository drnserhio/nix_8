import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {Account} from "../model/account";
import {Operation} from "../model/operation";
import {ResponseUserTablePage} from "../model/response-user-table-page";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  host = environment.host;

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<User[] | HttpErrorResponse> {
    return this.http.get<User[] | HttpErrorResponse>(`${this.host}/list-user`);
  }


  public create(user: User): Observable<void> {
    return this.http.post<void>(`${this.host}/create_user`, user);
  }


  public update(user: User): Observable<void> {
    return this.http.put<void>(`${this.host}/update_user`, user);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/delete_user/${id}`);
  }

  public findUserById(id: number): Observable<User | HttpErrorResponse> {
    return this.http.get<User | HttpErrorResponse>(`${this.host}/find_user/${id}"`);
  }

  public createAccount(account: Account, userId: number): Observable<void> {
    return this.http.post<void>(`${this.host}/create_account/${userId}`, account);
  }

  public updateAccount(account: Account): Observable<void> {
    return this.http.put<void>(`${this.host}/update_account`, account);
  }


  public deleteAccount(accountId: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/delete_account/${accountId}`);
  }

  public findAccountById(id: number): Observable<Account | HttpErrorResponse> {
    return this.http.get<Account | HttpErrorResponse>(`${this.host}/find_account/${id}`);
  }

  public findAccountByNameAccount(nameAccount: string): Observable<Account | HttpErrorResponse> {
    return this.http.get<Account | HttpErrorResponse>(`${this.host}/find_account_by_name/${nameAccount}`);
  }

  public findAllAccountsByUserId(userId: number): Observable<Account[] | HttpErrorResponse> {
    return this.http.get<Account[] | HttpErrorResponse>(`${this.host}/list_accounts_user/${userId}`);
  }

  public findOperationById(id: number): Observable<Operation | HttpErrorResponse> {
    return this.http.get<Operation | HttpErrorResponse>(`${this.host}/find_operation/${id}`);
  }

  public findByAccountId(accountId: number): Observable<Operation | HttpErrorResponse> {
    return this.http.get<Operation | HttpErrorResponse>(`${this.host}/find_operation_account/${accountId}`);
  }


  public sendMoney(senderId: number,
                   recipientId: number,
                   acccountSenderId: number,
                   accountRecipientId: number,
                   summa: number): Observable<void> {
    return this.http.get<void>(`${this.host}/send/send/${senderId}/${recipientId}/${acccountSenderId}/${accountRecipientId}/${summa}`);
  }

  public findAllWithSortColumn(page: number, showEntity: number, column: string, sort: string): Observable<ResponseUserTablePage | HttpErrorResponse> {
    return this.http.get<ResponseUserTablePage | HttpErrorResponse>(`${this.host}/limit-list/users/${page}/${showEntity}/${column}/${sort}`);
  }
}
