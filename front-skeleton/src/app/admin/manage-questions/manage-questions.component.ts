import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QuizAdminService, Quiz } from '../services/quiz-admin.service';

interface Question {
  id?: number;
  questionText: string;
  choice1?: string;
  choice2?: string;
  choice3?: string;
  choice4?: string;
  correctAnswer: string;
  points: number;
  quizId: number;
}

@Component({
  selector: 'app-manage-questions',
  templateUrl: './manage-questions.component.html',
  styleUrls: ['./manage-questions.component.scss']
})
export class ManageQuestionsComponent implements OnInit {

  questions: Question[] = [];
  quizzes: Quiz[] = [];
  newQuestion: Question = this.resetForm();
  loading = false;
  isEdit = false; // 🔹 mode édition activé ou non

  constructor(private http: HttpClient, private quizService: QuizAdminService) {}

  ngOnInit(): void {
    this.loadQuizzes();
    this.loadQuestions();
  }

  resetForm(): Question {
    return {
      questionText: '',
      choice1: '',
      choice2: '',
      choice3: '',
      choice4: '',
      correctAnswer: '',
      points: 5,
      quizId: 0
    };
  }

  // Charger tous les quiz
  loadQuizzes(): void {
    this.quizService.getAll().subscribe({
      next: (data) => (this.quizzes = data),
      error: (err) => console.error('Erreur chargement quiz:', err)
    });
  }

  // Charger toutes les questions
  loadQuestions(): void {
    this.loading = true;
    this.http.get<Question[]>('http://localhost:8082/api/admin/questions').subscribe({
      next: (data) => {
        this.questions = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur chargement questions:', err);
        this.loading = false;
      }
    });
  }

  // Ajouter ou modifier une question
  addOrUpdateQuestion(): void {
    if (!this.newQuestion.questionText || !this.newQuestion.correctAnswer || !this.newQuestion.quizId) return;

    const payload = {
      ...this.newQuestion,
      quiz: { id: this.newQuestion.quizId }
    };
    console.log("🟣 Payload envoyé :", payload); // 👈

    if (this.isEdit && this.newQuestion.id) {
      // 🔹 UPDATE
      this.http.put('http://localhost:8082/api/admin/questions/${this.newQuestion.id}', payload)
    .subscribe(() => {
        this.isEdit = false;
        this.newQuestion = this.resetForm();
        this.loadQuestions();
      });
    } else {
      // 🔹 ADD
      this.http.post('http://localhost:8082/api/admin/questions', payload)
        .subscribe(() => {
          this.newQuestion = this.resetForm();
          this.loadQuestions();
        });
    }
  }

  // Supprimer une question
  deleteQuestion(id: number): void {
    if (confirm('🗑 Supprimer cette question ?')) {
      this.http.delete('http://localhost:8082/api/admin/questions/${id}').subscribe(() => this.loadQuestions());
    }
  }

  // 🔹 Remplir le formulaire avec la question à modifier
  editQuestion(q: Question): void {
    this.newQuestion = { ...q };
    this.isEdit = true;
    window.scrollTo({ top: 0, behavior: 'smooth' }); // ramène le formulaire en haut
  }

  // 🔹 Annuler la modification
  cancelEdit(): void {
    this.isEdit = false;
    this.newQuestion = this.resetForm();
  }
  getQuizTitle(quizId: number): string {
    const quiz = this.quizzes.find(q => q.id === quizId);
    return quiz ? quiz.title : '—';
  }
}
