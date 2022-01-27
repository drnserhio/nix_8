import { Component, OnInit } from '@angular/core';
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {User} from "../model/user";
import {UserService} from "../service/user.service";
import {Router} from "@angular/router";
import {error} from "protractor";
import {HttpErrorResponse} from "@angular/common/http";
import {NgForm} from "@angular/forms";
import {Account} from "../model/account";
import {NotifierService} from "angular-notifier";

@Component({
  selector: 'app-select-user',
  templateUrl: './select-user.component.html',
  styleUrls: ['./select-user.component.css']
})
export class SelectUserComponent implements OnInit {
  private readonly notifier: NotifierService;

  sessionUser: User;
  users: User[] = [];
  private recipientUser: User;
  private senderAccountId: number;
  recipientAccountId: number;
  private senderAccount: Account;
  private summa: number;
  private sessionAccounts: Account[];
  constructor(private modalService: NgbModal,
              private userService: UserService,
              private router: Router,
              private notify: NotifierService) {
    this.notifier = notify;
  }

  ngOnInit() {
    this.getUserChache();
    this.takeAllUsers();
  }


  public openModal(content) {
    this.modalService.open(content, {size: 'lg'});
  }

  public close() {
    this.modalService.dismissAll();
  }

  private getUserChache() {
    if (this.userService.getsaveUserFromLocalCache()) {
      this.sessionUser = this.userService.getsaveUserFromLocalCache();
    }else {
      this.router.navigateByUrl('user/home');
    }
  }

  private takeAllUsers() {
     this.userService.findAll().subscribe(
       (response: User[]) => {
         this.users = response
           .filter(usr => usr.id != this.sessionUser.id);
       },
       (error: HttpErrorResponse) => {
         console.log(error);
       }
     );
  }


  public searchUsers(searchUsers: string): void {
    const res: User[] = [];
    for (const user of this.users) {

      console.log(user)
      if (user.firstname.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1 ||
        user.lastname.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1 ||
        user.username.toLowerCase().indexOf(searchUsers.toLowerCase()) !== -1) {
        res.push(user);
      }
    }
    this.users = res;
    if (res.length === 0 ||
      !searchUsers) {
      this.takeAllUsers();
    }
  }

  private onSelected(recipientId: number) {
    console.log(recipientId);
    this.userService.findUserById(recipientId).subscribe(
      (response: User) => {
        this.recipientUser = response;
        this.buttonClick('openModalRecipientUser');
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    )
  }

  private buttonClick(button: string) {
    document.getElementById(button).click();
  }

  selectSender(value: NgForm) {
    this.senderAccountId = value.controls['sender_user'].value;
    this.selectAccountFromUserSender(this.senderAccountId);
    console.log(this.senderAccountId);

  }

  selectRecipietn(value: NgForm) {
    this.recipientAccountId = value.controls['recipient_user'].value;
    console.log(this.recipientAccountId);
  }

  private selectAccountFromUserSender(accountId: number) {
    this.userService.findAccountById(accountId).subscribe(
      (response: Account) => {
        this.senderAccount = response;
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      }
    )
  }


  send() {
    this.userService.sendMoney(this.sessionUser.id, this.recipientUser.id, this.senderAccountId, this.recipientAccountId, this.summa).subscribe(
      (any ) => {
         alert('Succes send ' + this.summa);
         this.close();
         this.recipientAccountId = null;
    },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        alert("Don't send money...")
      }
    );
  }

  onMoney(value: NgForm) {
    this.summa = value.controls['money'].value;

  }

  public takeAllAcoountByUserId() {
    this.userService.findAllAccountsByUserId(this.sessionUser.id).subscribe(
      (response: Account[]) => {
        this.sessionAccounts = response;
      },
      (error: HttpErrorResponse) => {
        alert('Usert don\'t have accounts');
        this.close();
      }
    )
  }

  deleteAccount(id: number) {
    this.userService.deleteAccount(id).subscribe(
      (any) => {
        this.takeAllAcoountByUserId();
        alert('Account with : ' + id + " success remove.")
      },
      (error: HttpErrorResponse) => {
        alert('Field remove account');
      }
    )
  }

  onSelectSessionAccounts() {
    this.takeAllAcoountByUserId();
    this.buttonClick('openModalAccountsSessionUser');

  }

  onSelectSessionAndCreateAccount() {
    this.buttonClick('openModalCreateaccountsSessionUser');
  }

  toClose() {
    this.close();
  }

  createAccountForUser(account: Account) {

    console.log(account);
    this.userService.createAccount(account, this.sessionUser.id).subscribe(
      any => {
        alert("Account created successfull!");
        this.close();
      },
      (error: HttpErrorResponse) => {
        alert("Account didn't create successfull!" +
          "Change your field... ;)")
      }
    );
  }

  onDeleteUser() {
    this.userService.delete(this.sessionUser.id).subscribe(
      (value)=>  {
        localStorage.removeItem('saveUser');
        this.router.navigateByUrl('user/home');
    },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );

  }
}
