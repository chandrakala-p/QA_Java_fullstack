import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from '../answer';
import { IUser } from '../IUser';
import { Question } from '../question';
import { AnswerService } from '../services/answer.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {

  questions!: Question[];
  id!: number;
  question1: Question = new Question();
  ansList!: Answer[];
  question: any;
  questionId:any;
  isLoading = true;
  color = 'primary';
  mode = 'determinate';
  value = 50;
  displayedColumns = ['question', 'action', 'actionu', 'actiond'];
  msg1:any="";
  msg2:any="";

  constructor(private questionService: QuestionService,private answerService: AnswerService, private router: Router,) { }

  ngOnInit() {

    this.questionService.getAllApprovedQuestions().subscribe(data => {
      console.log(data);
      this.questions = data;
      this.isLoading = false;
    })
  }

  questionDetails(id: number) {
    this.router.navigate(['questionDetails', id]);
    console.log(id);
  }

  addAnswer(questionId:number) {
        this.router.navigate(['addAnswer/'+ questionId]);
        this.msg1 = "Please Login to add answers";
  }

  editQuestion(id: number) {

    this.router.navigate(['updateQuestion', id]);

    console.log(id);
  }

  addQuestion() {
    this.router.navigate(['addQuestion']);
    this.msg2 = "Please Login to add questions";
  }

  search() {
    this.isLoading = true;
    this.questions = this.questions.filter(res => {
      if (!this.questions) {
        this.questionService.getAllQuestions().subscribe(data => {
          this.questions = data;
          console.log(data);
        })
      }
      else {
        (error: any) => console.log(error);
      }
      return res.question.toLocaleLowerCase().match(this.question.toLocaleLowerCase());
    })
  }

  disQues : any = {}
  getAllAnswers(questionId:number){
    this.questionService.getQuestionById(questionId).subscribe(data=>{
      this.disQues = data
      console.log(data.question);
      
    })
    this.answerService.getallAnsByQuestionId(questionId).subscribe(data=>{
      this.ansList=data;
    })
  }

}
