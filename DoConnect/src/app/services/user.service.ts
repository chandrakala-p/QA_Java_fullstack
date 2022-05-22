import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IUser } from '../IUser';
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private restUrl:string='http://localhost:8080'

  constructor(private http:HttpClient) { }


  getUserFromService():Observable<IUser[]>{
    return this.http.get<IUser[]>(this.restUrl+ "/registerUser")
  }

  httpOptions = {
    headers : new HttpHeaders({
      'content-Type' : 'application/json'
    })
  }
  

  createUser(user:any):Observable<IUser>{
    console.log(user);
    
    return this.http.post<IUser>(this.restUrl+"/registerUser", JSON.stringify(user),this.httpOptions );
  }

  getUserById(id: any):Observable<IUser>{
    return this.http.get<IUser>(this.restUrl + '/getUserById/'+ id, this.httpOptions)

  }

  updateUser(user: any): Observable<IUser>{
    return this.http.put<IUser>(this.restUrl + '/updateUser/',JSON.stringify(user), this.httpOptions)
  }


  deleteUser(id:any){
    return this.http.delete<IUser>(this.restUrl+"/deleteUserById/"+id);
  }

getuserLogin(user: any){
  return this.http.put<IUser>(this.restUrl + '/getUserByEmailPass/',JSON.stringify(user), this.httpOptions)

}

}
