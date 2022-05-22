import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from '../answer';
import { Question } from '../question';
import { AnswerService } from '../services/answer.service';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-add-answer',
  templateUrl: './add-answer.component.html',
  styleUrls: ['./add-answer.component.css']
})
export class AddAnswerComponent implements OnInit {

  answer: Answer = new Answer();
  question: Question = new Question();
  questionId:any;
  user:any;
  submitted = false;

  constructor(private answerService: AnswerService,private questionService: QuestionService,  private route: ActivatedRoute,private router: Router) { }

  ngOnInit() {
    this.user=localStorage.getItem("authUser");
    this.user=JSON.parse(this.user);
    this.questionId = this.route.snapshot.params['questionId'];
    console.log(this.questionId);
  }

  newUser(): void {
    this.submitted = false;
    this.answer = new Answer();
  }

  save() {
    this.answerService.addAnswerByUserId(this.user.id,this.questionId,this.answer)
      .subscribe(data => console.log(data), error => console.log(error));
    this.answer = new Answer();
    this.answersList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }


  answersList() {
    this.router.navigate(['questions']);
  }

}
