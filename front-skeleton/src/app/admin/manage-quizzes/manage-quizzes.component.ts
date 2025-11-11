import { Component, OnInit } from '@angular/core';
import { QuizAdminService, Quiz } from '../services/quiz-admin.service';
import { HttpClient } from '@angular/common/http';

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
  isEdit = false;

  constructor(private quizService: QuizAdminService, private http: HttpClient) {}

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

  // Ajouter ou modifier un quiz
  addOrUpdateQuiz(): void {
    if (!this.newQuiz.title || !this.newQuiz.category) return;

    if (this.isEdit && this.newQuiz.id) {
      // 🔹 UPDATE
      this.http.put(`http://localhost:8082/api/admin/quizzes/${this.newQuiz.id}`, this.newQuiz)
        .subscribe(() => {
          this.isEdit = false;
          this.newQuiz = this.resetForm();
          this.loadQuizzes();
        });
    } else {
      // 🔹 ADD
      this.quizService.create(this.newQuiz).subscribe(() => {
        this.newQuiz = this.resetForm();
        this.loadQuizzes();
      });
    }
  }

  // Supprimer un quiz
  deleteQuiz(id: number): void {
    if (confirm('❌ Supprimer ce quiz ?')) {
      this.quizService.delete(id).subscribe(() => this.loadQuizzes());
    }
  }

  // Passer en mode édition
  editQuiz(q: Quiz): void {
    this.isEdit = true;
    this.newQuiz = { ...q };
  }

  // Annuler l’édition
  cancelEdit(): void {
    this.isEdit = false;
    this.newQuiz = this.resetForm();
  }

  // Réinitialiser le formulaire
  resetForm(): Quiz {
    return { title: '', category: '', description: '', level: 'facile' };
  }
}
