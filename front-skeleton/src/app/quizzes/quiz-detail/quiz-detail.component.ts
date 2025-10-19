import { Component, OnInit } from "@angular/core"
import { ActivatedRoute, Router } from '@angular/router';
import { QuizService } from 'services/quiz.service';
import { Quiz } from 'models/quiz.model';

@Component({
  selector: 'app-quiz-detail',
  templateUrl: './quiz-detail.component.html',
  styleUrls: ['./quiz-detail.component.scss']
})
export class QuizDetailComponent implements OnInit {

  quiz?: Quiz;
  loading = true;
  errorMessage = '';

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private quizService: QuizService
  ) {}

  ngOnInit(): void {
    const quizId = Number(this.route.snapshot.paramMap.get('id'));
    this.quizService.getById(quizId).subscribe({
      next: (data) => {
        this.quiz = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement du quiz.';
        console.error(err);
        this.loading = false;
      }
    });
  }

  startQuiz(): void {
    if (this.quiz) {
      this.router.navigate(['/quiz', this.quiz.id, 'play']);
    }
  }
}
