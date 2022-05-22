import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from '../question';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-update-question',
  templateUrl: './update-question.component.html',
  styleUrls: ['./update-question.component.css']
})
export class UpdateQuestionComponent implements OnInit {

  id!: number;
  question!: Question;
  submitted = false;

  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.question = new Question();
    this.id = this.route.snapshot.params['id'];
    this.questionService.getQuestionById(this.id).subscribe(data => {
      this.question = data;
      console.log(data);
    },
      error => console.log(error));
  }

  editQuestion() {
    console.log("update", this.question)
    this.questionService.updateQuestion(this.question).
      subscribe(data => console.log(data), error => console.log(error));
    this.question = new Question();

    this.questionsList();

  }

  onSubmit() {
    this.submitted = true;
    this.editQuestion();
  }

  questionsList() {
    this.router.navigate(['questions']);
  }

}

