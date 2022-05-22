import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  password1 : string = ''
  password2 : string = ''
  msg = ''
  constructor(public userService:UserService ,private route:Router) { }
 
  user= {
    userName : "",
    password : "",
    userEmail : "",
  }

  ngOnInit(): void {
  }



  newUser(){
    console.log('logged');
    
    this.msg = ''
    if(this.user.password == this.password2){
      console.log(this.user);
      
      this.userService.createUser(this.user).subscribe(data=> this.route.navigate(['/login']))
       
    }else{
      this.msg = 'password is incorrect'

    }
  }
}
