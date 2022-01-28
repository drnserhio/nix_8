import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {User} from "../model/user";
import {Account} from "../model/account";
import {Operation} from "../model/operation";
import {ResponseUserTablePage} from "../dto/response-user-table-page";
import {ResponseTablePage} from "../dto/response-table-page";

@Injectable({
  providedIn: 'root'
})
export class UserService {

 private host = environment.host;

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<User[] | HttpErrorResponse> {
    return this.http.get<User[] | HttpErrorResponse>(`${this.host}/user/list-user`);
  }


  public create(user: User): Observable<void> {
    return this.http.post<void>(`${this.host}/user/create_user`, user);
  }


  public update(user: User): Observable<void> {
    return this.http.put<void>(`${this.host}/user/update_user`, user);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/user/delete_user/${id}`);
  }

  public findUserById(id: number): Observable<User | HttpErrorResponse> {
    return this.http.get<User | HttpErrorResponse>(`${this.host}/user/find_user/${id}`);
  }

  public createAccount(account: Account, userId: number): Observable<void> {
    return this.http.post<void>(`${this.host}/user/create_account/${userId}`, account);
  }

  public updateAccount(account: Account): Observable<void> {
    return this.http.put<void>(`${this.host}/user/update_account`, account);
  }


  public deleteAccount(accountId: number): Observable<void> {
    return this.http.delete<void>(`${this.host}/user/delete_account/${accountId}`);
  }

  public findAccountById(id: number): Observable<Account | HttpErrorResponse> {
    return this.http.get<Account | HttpErrorResponse>(`${this.host}/user/find_account/${id}`);
  }

  public findAccountByNameAccount(nameAccount: string): Observable<Account | HttpErrorResponse> {
    return this.http.get<Account | HttpErrorResponse>(`${this.host}/user/find_account_by_name/${nameAccount}`);
  }

  public findAllAccountsByUserId(userId: number): Observable<Account[] | HttpErrorResponse> {
    return this.http.get<Account[] | HttpErrorResponse>(`${this.host}/user/list_accounts_user/${userId}`);
  }

  public findOperationById(id: number): Observable<Operation | HttpErrorResponse> {
    return this.http.get<Operation | HttpErrorResponse>(`${this.host}/user/find_operation/${id}`);
  }

  public findByAccountId(accountId: number): Observable<Operation | HttpErrorResponse> {
    return this.http.get<Operation | HttpErrorResponse>(`${this.host}/user/find_operation_account/${accountId}`);
  }


  public sendMoney(senderId: number,
                   recipientId: number,
                   acccountSenderId: number,
                   accountRecipientId: number,
                   summa: number): Observable<boolean | HttpErrorResponse> {
    return this.http.get<boolean | HttpErrorResponse>(`${this.host}/user/send/${senderId}/${recipientId}/${acccountSenderId}/${accountRecipientId}/${summa}`);
  }

  public findAllUserListPage(page: number, showEntity: number, column: string, sort: string): Observable<ResponseTablePage<User> | HttpErrorResponse> {
    return this.http.get<ResponseTablePage<User> | HttpErrorResponse>(`${this.host}/user/limit-list/users/${page}/${showEntity}/${column}/${sort}`);
  }

  public findAllAccountForUserListPage(page: number, showEntity: number, column: string, sort: string, userId: number): Observable<ResponseTablePage<Account> | HttpErrorResponse> {
    return this.http.get<ResponseTablePage<Account> | HttpErrorResponse>(`${this.host}/user/limit-accounts_user/users/${page}/${showEntity}/${column}/${sort}/${userId}`);
  }

  public saveSelectUserTolocalCache(user: User) {
    localStorage.setItem('saveUser', JSON.stringify(user));
  }

  public getsaveUserFromLocalCache() {
    return JSON.parse(localStorage.getItem('saveUser'));
  }

  public downloadOperationCSV(userId: number): Observable<any>{
  return this.http.get(`${this.host}/user/download/csv/${userId}`, { responseType: 'blob'});
}
}
