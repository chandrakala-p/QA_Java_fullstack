import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from '../answer';
import { AnswerService } from '../services/answer.service';

@Component({
  selector: 'app-answer-details',
  templateUrl: './answer-details.component.html',
  styleUrls: ['./answer-details.component.css']
})
export class AnswerDetailsComponent implements OnInit {

  id!: number;
  answer!: Answer;

  constructor(private answerService: AnswerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {

    this.answer = new Answer();
    this.id = this.route.snapshot.params['id'];

    this.answerService.getAnswerById(this.id)
      .subscribe(data => {
        console.log(data)
        this.answer = data;
      }, error => console.log(error));
  }

  answersList() {
    this.router.navigate(['answers']);
  }

}