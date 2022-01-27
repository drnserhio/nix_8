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
  private recipientAccountId: number;
  private senderAccount: Account;
  private summa: number;
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
    },
      (error: HttpErrorResponse) => {
        console.log(error.message);
      }
    );
  }

  onMoney(value: NgForm) {
    this.summa = value.controls['money'].value;
  }
}
