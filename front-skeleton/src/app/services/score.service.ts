import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Score } from '../models/score.model';

@Injectable({ providedIn: 'root' })
export class ScoreService {
  private apiUrl = 'http://localhost:8082/api/scores';

  constructor(private http: HttpClient) {}

  getUserScores(userId: number): Observable<Score[]> {
    return this.http.get<Score[]>(`${this.apiUrl}/user/${userId}`);
  }

  saveScore(score: Score): Observable<Score> {
    return this.http.post<Score>(this.apiUrl, score);
  }

  getLeaderboard(quizId: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/leaderboard/${quizId}`);
  }
}
