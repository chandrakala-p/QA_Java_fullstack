import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IAdmin } from '../IAdmin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private restUrl:string='http://localhost:8080'

  constructor(private http:HttpClient) { }

  getAdminFromService():Observable<IAdmin[]>{
    return this.http.get<IAdmin[]>(this.restUrl+ "/register")
  }

  httpOptions = {
    headers : new HttpHeaders({
      'content-Type' : 'application/json'
    })
  }

  createAdmin(admin:any):Observable<IAdmin>{
  console.log(admin);
    
    return this.http.post<IAdmin>(this.restUrl+"/register", JSON.stringify(admin),this.httpOptions );
  }

  getAdminById(id: any):Observable<IAdmin>{
    return this.http.get<IAdmin>(this.restUrl + '/getAdminById/'+ id, this.httpOptions)

  }

  updateAdmin(admin: any): Observable<IAdmin>{
    return this.http.put<IAdmin>(this.restUrl + '/updateAdmin/',JSON.stringify(admin), this.httpOptions)
  }


  deleteAdmin(id:any){
    return this.http.delete<IAdmin>(this.restUrl+"/deleteAdminById/"+id);
  }

  getadminLogin(admin: any){
   return this.http.put<IAdmin>(this.restUrl + '/getAdminByEmailPass/',JSON.stringify(admin), this.httpOptions)

}
  
public approveQuestionByAdmin(adminId: number,questionId: number): Observable<object> {
  return this.http.post<IAdmin>(this.restUrl+"/approveQuestionByAdmin/"+adminId+"/"+questionId,this.httpOptions );
}
public approveAnswerByAdmin(adminId: number,answerId: number): Observable<object> {
  return this.http.post<IAdmin>(this.restUrl+"/approveAnswerByAdmin/"+adminId+"/"+answerId,this.httpOptions );
}
}

