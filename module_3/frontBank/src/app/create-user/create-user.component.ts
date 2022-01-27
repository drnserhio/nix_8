import { Component, OnInit } from '@angular/core';
import {User} from "../model/user";
import {UserService} from "../service/user.service";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  constructor(private userService: UserService,
              private router: Router) { }

  ngOnInit() {

  }

  createUser(user: User) {
    console.log(user);
    this.userService.create(user).subscribe(
      (any) => {
        alert('User create');
      },
      (error: HttpErrorResponse) => {
        alert('User don\'t create');
      }
    )
  }

  toHome() {
    this.router.navigateByUrl('user/home')
  }
}
