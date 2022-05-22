import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Question } from '../question';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  question: Question = new Question();
  user:any;
  submitted1 = false;

  constructor(private questionService: QuestionService, private router: Router) { }

  ngOnInit() {
    this.user=localStorage.getItem("authUser");
    this.user=JSON.parse(this.user);
  }

  newUser(): void {
    this.submitted1 = false;
    this.question = new Question();
  }

  save() {
    this.questionService.addQuestionByUserId(this.question,this.user.id)
      .subscribe(data => console.log(data), error => console.log(error));
    this.question = new Question();
    this.questionsList();
  }
  questionsList() {
    this.router.navigate(['questions']);
  }

  onSubmit() {
    this.submitted1 = true;
    this.save();
  }

}

