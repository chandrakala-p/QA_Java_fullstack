import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IUser } from '../IUser';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  authUser : any = {};
  msg:any="";
  user= {
    password : "",
    userEmail : "",
  }
  constructor(private route:Router,public userService:UserService) { }

  ngOnInit(): void {
  }

  login(){
    this.userService.getuserLogin(this.user).subscribe(data=>{
      console.log(data);
      this.authUser = data;
    localStorage.setItem("authUser", JSON.stringify(this.authUser)),
    localStorage.setItem("role","user");
    this.route.navigate(['questions']);
  },error=>{
      this.msg = "Bad credentials Enter valid info"} )
    
  }
  register(){
    this.route.navigate(['register'])
  }
}
