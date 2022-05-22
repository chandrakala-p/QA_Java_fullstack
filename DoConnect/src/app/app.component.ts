import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  role:any;
  constructor(private router:Router,public userService:UserService) { }
  
  ngOnInit(): void {
    this.role=localStorage.getItem("role");
    console.log(this.role);
  }
logout(){
  localStorage.removeItem("authUser");
  localStorage.removeItem("role");
  this.router.navigate(['home']);
}
adminlogout(){
  localStorage.removeItem("authUser");
  localStorage.removeItem("role");
  this.router.navigate(['home']);
}
  title = 'DoConnect';
}