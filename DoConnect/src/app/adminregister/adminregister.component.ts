import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-adminregister',
  templateUrl: './adminregister.component.html',
  styleUrls: ['./adminregister.component.css']
})
export class AdminregisterComponent implements OnInit {
  password1 : string = ''
  password2 : string = ''
  msg = ''

  constructor(public adminService:AdminService ,private route:Router) { }
  admin= {
    adminName : "",
    password : "",
    adminEmail : "",
  }

  ngOnInit(): void {
  }
  newAdmin(){
    console.log('logged');
    this.msg = ''
    if(this.admin.password == this.password2){
      console.log(this.admin);
      this.adminService.createAdmin(this.admin).subscribe(data=> this.route.navigate(['/admin']) )
       
    }else{
      this.msg = 'password is incorrect'

    }
  }
}
