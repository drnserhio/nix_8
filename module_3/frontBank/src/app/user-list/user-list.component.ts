import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user.service";
import {User} from "../model/user";
import {ResponseUserTablePage} from "../dto/response-user-table-page";
import {HttpErrorResponse} from "@angular/common/http";
import {NotifierService} from "angular-notifier";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {


  private selectUser = new User();

  private responseUserTablePage: ResponseUserTablePage;
  private users: User[] = [];
  private page = 1;
  private showEntity = 4;
  private column = 'id';
  private sort = 'asc';
  pageSizes = [4, 8 ,12, 16, 20, 24]

  constructor(private userService: UserService,
              private notify: NotifierService,
              private modalService: NgbModal,
              private router: Router) { }

  ngOnInit() {
    this.sortUserPage(this.page, this.showEntity, this.column, this.sort);
  }

  private sortUserPage(page: number, showEntity: number, column: string, sort: string) {
    this.userService.findAllUserListPage(page, showEntity, column, sort).subscribe(
      (response: ResponseUserTablePage) => {
        console.log(response);
        this.notify.notify('success', response.totalPages.toString());
        this.responseUserTablePage = response;
        this.users = response.content;
      },
      (error: HttpErrorResponse) => {
        console.log(error.message);
        this.notify.notify('error', error.message);
      }
    )
  }


  pageChange(event) {
    console.log(event)
    this.page = event;
    this.sortUserPage(this.page, this.showEntity, this.column, this.sort);
  }

  pageChangeShowEntity(event) {
    this.showEntity = event.target.value;
    this.page = 1;
    this.sortUserPage(this.page, this.showEntity, this.column, this.sort);
  }

  public sortByColumn(event) {
    this.column = event.srcElement.id;
    if (this.sort.endsWith('asc')) {
      this.sort = 'desc';
      this.page = 1;
      this.sortUserPage(this.page, this.showEntity, this.column, this.sort);

    } else {
      this.sort = 'asc';
      this.page = 1;
      this.sortUserPage(this.page, this.showEntity, this.column, this.sort);
    }
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
      this.sortUserPage(this.page, this.showEntity, this.column, this.sort);
    }
  }

  public onSelect(user: User) {
    this.userService.saveSelectUserTolocalCache(user);
    this.router.navigateByUrl("select_user");
  }

}
