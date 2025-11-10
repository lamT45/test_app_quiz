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
        console.log('📦 Quiz reçu du backend :', this.quiz);

        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement du quiz.';
        console.error(err);
        this.loading = false;
      }
    });
  }
  getDifficultyClass(level: string): string {
    if (!level) return '';
    const lvl = level.toLowerCase();
    if (lvl === 'facile') return 'easy';
    if (lvl === 'moyen') return 'medium';
    if (lvl === 'difficile') return 'hard';
    return '';
  }
  goBack(): void {
    this.router.navigate(['/quiz']);
  }

  startQuiz(quizId: number) {
    const token = localStorage.getItem('token');

    if (token) {
      //  Utilisateur connecté → accéder directement au quiz
      this.router.navigate(['/play', quizId]);
    } else {
      //  Pas connecté → sauvegarde la page actuelle et redirige vers login
      const currentUrl = this.router.url;
      localStorage.setItem('redirectUrl', currentUrl);
      console.log('💾 redirectUrl sauvegardée :', currentUrl);

      this.router.navigate(['/login']);
    }
  }


}

