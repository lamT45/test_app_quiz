import { Component, OnInit } from '@angular/core';
import { QuizAdminService, Quiz } from '../services/quiz-admin.service';

@Component({
  selector: 'app-manage-quizzes',
  templateUrl: './manage-quizzes.component.html',
  styleUrls: ['./manage-quizzes.component.scss']
})
export class ManageQuizzesComponent implements OnInit {

  quizzes: Quiz[] = [];
  newQuiz: Quiz = { title: '', category: '', description: '', level: 'facile' };
  levels = ['facile', 'moyen', 'difficile'];
  loading = false;

  constructor(private quizService: QuizAdminService) {}

  ngOnInit(): void {
    this.loadQuizzes();
  }

  // Charger la liste des quiz
  loadQuizzes(): void {
    this.loading = true;
    this.quizService.getAll().subscribe({
      next: (data) => {
        this.quizzes = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement quiz:', err);
        this.loading = false;
      }
    });
  }

  // Ajouter un quiz
  addQuiz(): void {
    if (!this.newQuiz.title || !this.newQuiz.category) return;

    this.quizService.create(this.newQuiz).subscribe(() => {
      this.newQuiz = { title: '', category: '', description: '', level: 'facile' };
      this.loadQuizzes();
    });
  }

  // Supprimer un quiz
  deleteQuiz(id: number): void {
    if (confirm('❌ Supprimer ce quiz ?')) {
      this.quizService.delete(id).subscribe(() => this.loadQuizzes());
    }
  }
}
