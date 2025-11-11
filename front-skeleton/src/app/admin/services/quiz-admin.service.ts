import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

// Interface représentant un quiz
export interface Quiz {
  id?: number;
  title: string;
  category: string;
  description: string;
  level: string;
  duration?: number;
}

@Injectable({
  providedIn: 'root'
})
export class QuizAdminService {

  private apiUrl = 'http://localhost:8082/api/admin/quizzes';

  constructor(private http: HttpClient) {}

  // 🔹 Récupérer tous les quiz
  getAll(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(this.apiUrl);
  }

  // 🔹 Ajouter un quiz
  create(quiz: Quiz): Observable<Quiz> {
    return this.http.post<Quiz>(this.apiUrl, quiz);
  }

  // 🔹 Supprimer un quiz
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Modifier un quiz
  update(id: number, quiz: Quiz): Observable<Quiz> {
    return this.http.put<Quiz>(`${this.apiUrl}/${id}`, quiz);
  }

}
