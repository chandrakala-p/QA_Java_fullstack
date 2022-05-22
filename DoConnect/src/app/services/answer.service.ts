import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  public baseUrl = "http://localhost:8080/";

  constructor(private httpClient: HttpClient) {

  }

  public getAllAnswers(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getAllAnswers`);
  }

  public getAnswerById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getAnswerById/${id}`);
  }

  public deleteAnswerById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/deleteAnswerById/${id}`, { responseType: 'text' });
  }

  public updateAnswer(value: any): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/updateAnswer/`, value);
  }

  public addAnswer(user: object): Observable<object> {
    return this.httpClient.post(`${this.baseUrl}/addAnswer`, user);
  }

  public addAnswerByUserId(userId:number,questionId:number,answer: object):Observable<object>{
    return this.httpClient.post(`${this.baseUrl}/addAnswerByUserId/${userId}/${questionId}`, answer);
  }
  public getAllApprovedAnswers():Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getAllApprovedAnswers`);
  }
  public getallAnsByQuestionId(questionId:number):Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getallAnsByQuestionId/${questionId}`);
  }
}