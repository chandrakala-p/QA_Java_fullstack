import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Answer } from '../answer';
import { AnswerService } from '../services/answer.service';

@Component({
  selector: 'app-answer-list',
  templateUrl: './answer-list.component.html',
  styleUrls: ['./answer-list.component.css']
})
export class AnswerListComponent implements OnInit {

  answers!: Answer[];
  id!: number;
  answer: Answer = new Answer();
  answerString: any;

  isLoading = true;
  color = 'primary';
  mode = 'determinate';
  value = 50;
  displayedColumns = ['answerString', 'action', 'actionu', 'actiond'];


  constructor(private answerService: AnswerService, private router: Router,) { }

  ngOnInit() {

    this.answerService.getAllApprovedAnswers().subscribe(data => {
      console.log(data);
      this.answers = data;
      this.isLoading = false;
    })
  }

  answerDetails(id: number) {
    this.router.navigate(['answerDetails', id]);
    console.log(id);
  }

  deleteAnswer(id: number) {
    this.answerService.deleteAnswerById(id).
      subscribe(data => {
        console.log(data);
        this.ngOnInit();
      },
        error => console.log(error));
  }

  editAnswer(id: number) {

    this.router.navigate(['updateAnswer', id]);

    console.log(id);
  }

  addAnswer() {
    this.router.navigate(['addAnswer']);
  }

  search() {
    this.isLoading = true;
    this.answers = this.answers.filter(res => {
      if (!this.answers) {
        this.answerService.getAllAnswers().subscribe(data => {
          this.answers = data;
          console.log(data);
        })
      }
      else {
        (error: any) => console.log(error);
      }
      return res.answerString.toLocaleLowerCase().match(this.answerString.toLocaleLowerCase());
    })
  }



}
