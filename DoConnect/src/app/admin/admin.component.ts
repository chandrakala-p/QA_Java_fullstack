
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
  authUser : any = {};
  admin= {
    password : "",
    adminEmail : "",
  }

  constructor(private route:Router,public adminService:AdminService) { }

  ngOnInit(): void {
  }
  msg:any="";

  loginAdmin(): void{
    this.adminService.getadminLogin(this.admin).subscribe((data: any)=>{
      console.log(data);
      this.authUser = data;
    localStorage.setItem("authUser", JSON.stringify(this.authUser)),
    localStorage.setItem("role","admin");
    this.route.navigate(['adminpage']);
  },error=>{
      this.msg = "Bad credentials Enter valid info"} )
    
  }
  adminreg(){
    this.route.navigate(['adminregister'])

}
  

}