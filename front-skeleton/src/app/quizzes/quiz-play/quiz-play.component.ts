import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-quiz-play',
  templateUrl: './quiz-play.component.html',
  styleUrls: ['./quiz-play.component.scss']
})
export class QuizPlayComponent implements OnInit {

  quizId!: number;
  questions: any[] = [];
  currentIndex = 0;
  score = 0;
  selectedChoice: string | null = null;
  showResult = false;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.quizId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadQuestions();
  }

  loadQuestions(): void {
    this.http.get<any[]>(`http://localhost:5000/api/quizzes/${this.quizId}/questions`).subscribe({
      next: (data) => this.questions = data,
      error: (err) => console.error('Erreur lors du chargement des questions', err)
    });
  }

  selectChoice(choice: string): void {
    this.selectedChoice = choice;
  }

  nextQuestion(): void {
    const currentQuestion = this.questions[this.currentIndex];
    const choices = JSON.parse(currentQuestion.choices);
    const correctChoice = choices.find((c: any) => c.correct === true);

    if (this.selectedChoice === correctChoice.text) {
      this.score += currentQuestion.points;
    }

    this.selectedChoice = null;
    this.currentIndex++;

    if (this.currentIndex >= this.questions.length) {
      this.showResult = true;
    }
  }

  restartQuiz(): void {
    this.currentIndex = 0;
    this.score = 0;
    this.showResult = false;
  }

  goBack(): void {
    this.router.navigate(['/']);
  }
}
