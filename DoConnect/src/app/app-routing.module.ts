import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { AdminpageComponent } from './adminpage/adminpage.component';
import { AdminregisterComponent } from './adminregister/adminregister.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { QuestionsComponent } from './questions/questions.component';
import { RegisterComponent } from './register/register.component';
import { AnswerDetailsComponent } from './answer-details/answer-details.component';
import { AnswerListComponent } from './answer-list/answer-list.component';
import { QuestionDetailsComponent } from './question-details/question-details.component';
import { UpdateAnswerComponent } from './update-answer/update-answer.component';
import { UpdateQuestionComponent } from './update-question/update-question.component';
import { AddQuestionComponent } from './add-question/add-question.component';
import { AddAnswerComponent } from './add-answer/add-answer.component';
import { UserpageComponent } from './userpage/userpage.component';
import { AuthenticationGuard } from './authentication.guard';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'admin', component: AdminComponent },
  { path: 'adminpage', component: AdminpageComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['admin'] }, 
  }, 
  { path: 'adminregister', component: AdminregisterComponent },

  { path: 'questions', component: QuestionsComponent },
  { path: 'addQuestion', 
    component: AddQuestionComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] }, 
  },
  { path: 'updateQuestion/:id', 
    component: UpdateQuestionComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] }, 
  },
  { path: 'questionDetails/:id', component: QuestionDetailsComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] }, 
  },

  { path: 'answers', component: AnswerListComponent },
  { path: 'addAnswer/:questionId', component: AddAnswerComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] }, 
  },
  { path: 'updateAnswer/:id', component: UpdateAnswerComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] },  
  },
  { path: 'answerDetails/:id', component: AnswerDetailsComponent,
    canActivate: [AuthenticationGuard], 
    data: { Role: ['user'] },   
  },

  { path: 'userpage', component: UserpageComponent,
  canActivate: [AuthenticationGuard], 
  data: { Role: ['user'] },   
},
  
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
