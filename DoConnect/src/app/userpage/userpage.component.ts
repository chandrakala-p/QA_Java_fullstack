import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from '../answer';
import { Question } from '../question';
import { AnswerService } from '../services/answer.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-userpage',
  templateUrl: './userpage.component.html',
  styleUrls: ['./userpage.component.css']
})
export class UserpageComponent implements OnInit {

  questions!: Question[];
  id!: number;
  userId!: number;
  question1: Question = new Question();
  question: any;
  
  answers!: Answer[];
  answer: Answer = new Answer();
  answerString: any;
  
  isLoading = true;
  color = 'primary';
  mode = 'determinate';
  value = 50;
  displayedColumns = ['question', 'action', 'actionu', 'actiond'];
  constructor(private questionService: QuestionService,private answerService: AnswerService, private router: Router,) { }

  ngOnInit(): void {
    this.questionService.getAllApprovedQuestions().subscribe(data=>{
      console.log(data);
      this.questions=data;
    }),
    this.answerService.getAllApprovedAnswers().subscribe(data=>{
      console.log(data);
      this.answers=data;
    })
  }

  getQuestionsByUserId(userId: number) {
    this.questionService.getallQuestionByUserId(this.userId).
    subscribe((data) => {
        console.log(data);
        this.ngOnInit();
      },( error) => console.log(error));
    }
}
