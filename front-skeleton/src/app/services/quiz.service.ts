import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Quiz } from '../models/quiz.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  private baseUrl = `${environment.apiUrl}/quizzes`;

  constructor(private http: HttpClient) {}

  // Récupérer tous les quiz depuis ton backend Spring Boot
  getAll(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.baseUrl);
  }

  // Récupérer un quiz par son ID
  getById(id: number): Observable<Quiz> {
    return this.http.get<Quiz>(`${this.baseUrl}/${id}`);
  }

  // Récupérer les questions liées à un quiz
  getQuestionsByQuizId(quizId: number): Observable<any[]> {
    return this.http.get<any[]>(`${environment.apiUrl}/questions?quizId=${quizId}`);
  }

  // Créer un nouveau quiz
  createQuiz(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(this.baseUrl, quiz);
  }

  // Supprimer un quiz
  deleteQuiz(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
  // Incrémenter le nombre de joueurs pour un quiz
  incrementPlayers(quizId: number) {
    return this.http.put<any>(`${this.baseUrl}/${quizId}/increment-players`, {});
  }
}
