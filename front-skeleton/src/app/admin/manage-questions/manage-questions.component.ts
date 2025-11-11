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
  newQuestion: Question = {
    questionText: '',
    choice1: '',
    choice2: '',
    choice3: '',
    choice4: '',
    correctAnswer: '',
    points: 5,
    quizId: 0
  };
  loading = false;

  constructor(private http: HttpClient, private quizService: QuizAdminService) {}

  ngOnInit(): void {
    this.loadQuizzes();
    this.loadQuestions();
  }

  // Charger tous les quiz (pour la liste déroulante)
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

  // Ajouter une nouvelle question
  addQuestion(): void {
    if (!this.newQuestion.questionText || !this.newQuestion.correctAnswer || !this.newQuestion.quizId) return;

    const payload = {
      ...this.newQuestion,
      quiz: { id: this.newQuestion.quizId } // 👈 on envoie un objet quiz
    };

    this.http.post('http://localhost:8082/api/admin/questions', payload).subscribe(() => {
      this.newQuestion = {
        questionText: '',
        choice1: '',
        choice2: '',
        choice3: '',
        choice4: '',
        correctAnswer: '',
        points: 5,
        quizId: 0
      };
      this.loadQuestions();
    });
  }
  // Supprimer une question
  deleteQuestion(id: number): void {
    if (confirm('🗑️ Supprimer cette question ?')) {
      this.http.delete(`http://localhost:8082/api/admin/questions/${id}`).subscribe(() => this.loadQuestions());
    }
  }
}
