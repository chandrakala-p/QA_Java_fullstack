import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {
  constructor(private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    var role;
    role = localStorage.getItem('role');

    if (role == null) { 
      role = " "
    }

    console.log(role);
    console.log(route.data['Role']);


    const isAuthorized = role.includes(route.data['Role']);
    if (!isAuthorized) {
      this.router.navigate(['/login']);
      alert("Cannot accessible");
    } else {
      return true;
    }
    return false;
  }
}
