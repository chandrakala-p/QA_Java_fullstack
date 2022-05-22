import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Answer } from '../answer';
import { AnswerService } from '../services/answer.service';

@Component({
  selector: 'app-update-answer',
  templateUrl: './update-answer.component.html',
  styleUrls: ['./update-answer.component.css']
})
export class UpdateAnswerComponent implements OnInit {

  id!: number;
  answer!: Answer;
  submitted = false;

  constructor(private answerService: AnswerService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.answer = new Answer();
    this.id = this.route.snapshot.params['id'];
    this.answerService.getAnswerById(this.id).subscribe(data => {
      this.answer = data;
      console.log(data);
    },
      error => console.log(error));
  }

  editAnswer() {
    console.log("update", this.answer)
    this.answerService.updateAnswer(this.answer).
      subscribe(data => console.log(data), error => console.log(error));
    this.answer = new Answer();


    this.answersList();

  }

  onSubmit() {
    this.submitted = true;
    this.editAnswer();
  }

  answersList() {
    this.router.navigate(['answers']);
  }

}

