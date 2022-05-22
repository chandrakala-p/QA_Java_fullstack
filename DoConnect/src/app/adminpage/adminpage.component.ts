import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from '../answer';
import { IAdmin } from '../IAdmin';
import { Question } from '../question';
import { AdminService } from '../services/admin.service';
import { AnswerService } from '../services/answer.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-adminpage',
  templateUrl: './adminpage.component.html',
  styleUrls: ['./adminpage.component.css']
})
export class AdminpageComponent implements OnInit {

  questions!: Question[];
  id!: number;
  
  adminId: any;
  questionId!: number;
  question1: Question = new Question();
  question: any;
  
  answers!: Answer[];
  answerId!: number;
  answer: Answer = new Answer();
  answerString: any;

  admin!: IAdmin[];
  
  isLoading = true;
  color = 'primary';
  mode = 'determinate';
  value = 50;
  displayedColumns = ['question', 'action', 'actionu', 'actiond'];
  constructor(private questionService: QuestionService,private answerService: AnswerService,private adminService: AdminService, private router: Router,) { }

  ngOnInit(): void {
    this.adminId=localStorage.getItem("authUser");
    this.adminId=JSON.parse(this.adminId);
    console.log(this.adminId);
    console.log(this.adminId.id);
    this.questionService.getAllQuestions().subscribe(data => {
      console.log(data);
      this.questions = data;
      this.isLoading = false;
    }), 

    this.answerService.getAllAnswers().subscribe(data => {
      console.log(data);
      this.answers = data;
      this.isLoading = false;
    })
  }
approveQuestions(adminId: number,id: number){
  console.log(this.adminId.id);
  this.adminService.approveQuestionByAdmin(this.adminId.id,id).subscribe(data=> {
    console.log(data);
    this.isLoading = false; 
    this.ngOnInit();
  })
}
approveAnswers(adminId: number,id: number){
  console.log(this.adminId.id);
  console.log(id);
  this.adminService.approveAnswerByAdmin(this.adminId.id,id).subscribe(data=> {
    console.log(data);
    this.isLoading = false; 
    this.ngOnInit();
  })
}
deleteQuestion(id: number) {
  this.questionService.deleteQuestionById(id).
  subscribe((data) => {
      console.log(data);
      this.ngOnInit();
    },( error) => console.log(error));
}
deleteAnswer(id: number) {
  this.answerService.deleteAnswerById(id).
    subscribe(data => {
      console.log(data);
      this.ngOnInit();
    },
      error => console.log(error));
}
}
