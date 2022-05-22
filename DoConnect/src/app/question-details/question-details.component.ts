import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Question } from '../question';
import { QuestionService } from '../services/question.service';

@Component({
  selector: 'app-question-details',
  templateUrl: './question-details.component.html',
  styleUrls: ['./question-details.component.css']
})
export class QuestionDetailsComponent implements OnInit {

  id!: number;
  question!: Question;

  constructor(private questionService: QuestionService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.question = new Question();
    this.id = this.route.snapshot.params['id'];

    this.questionService.getQuestionById(this.id)
      .subscribe(data => {
        console.log(data)
        this.question = data;
      }, error => console.log(error));
  }

  questionsList() {
    this.router.navigate(['questions']);
  }

}
