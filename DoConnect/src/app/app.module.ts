import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { QuestionsComponent } from './questions/questions.component';
import { RegisterComponent } from './register/register.component';

import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { HttpClientModule } from '@angular/common/http';
import { LogoutComponent } from './logout/logout.component';
import { AdminregisterComponent } from './adminregister/adminregister.component';

import { AddQuestionComponent } from './add-question/add-question.component';
import { UpdateQuestionComponent } from './update-question/update-question.component';
import { QuestionDetailsComponent } from './question-details/question-details.component';
import { AnswerListComponent } from './answer-list/answer-list.component';
import { AddAnswerComponent } from './add-answer/add-answer.component';
import { UpdateAnswerComponent } from './update-answer/update-answer.component';
import { AnswerDetailsComponent } from './answer-details/answer-details.component';
import { UserpageComponent } from './userpage/userpage.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    HomeComponent,
    
    AdminComponent,
    AdminpageComponent,
    AdminregisterComponent,

    QuestionsComponent,
    AddQuestionComponent,
    UpdateQuestionComponent,
    QuestionDetailsComponent,

    AnswerListComponent,
    AddAnswerComponent,
    UpdateAnswerComponent,
    AnswerDetailsComponent,
    UserpageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
