import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  public baseUrl = "http://localhost:8080";

  constructor(private httpClient: HttpClient) {

  }

  public getAllQuestions(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getAllQuestions`);
  }

  public getQuestionById(id: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getQuestionById/${id}`);
  }

  public deleteQuestionById(id: number): Observable<any> {
    return this.httpClient.delete(`${this.baseUrl}/deleteQuestionById/${id}`, { responseType: 'text' });
  }

  public updateQuestion(value: any): Observable<any> {
    return this.httpClient.put(`${this.baseUrl}/updateQuestion/`, value);
  }

  public addQuestion(user: object): Observable<object> {
    return this.httpClient.post(`${this.baseUrl}/addQuestion`, user);
  }

  public getallQuestionByUserId(userId: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/getallQuestionByUserId/${userId}`);
  }
  public addQuestionByUserId(question: object,userId: number): Observable<any>{
    return this.httpClient.post(`${this.baseUrl}/addQuestionByUserId/${userId}`,question);
  }
  public getAllApprovedQuestions():Observable<any>{
    return this.httpClient.get(`${this.baseUrl}/getAllApprovedQuestions`);
  }
  public addAnswerByUserId(userId:number,answer: object):Observable<object>{
    return this.httpClient.post(`${this.baseUrl}/addAnswerByUserId/${userId}`, answer);
  }
  public addAnswersToQuestion(questionId:number,answerId:number){
    return this.httpClient.get(`${this.baseUrl}/addAnswersToQuestion/${questionId}/${answerId}`);
  }
}