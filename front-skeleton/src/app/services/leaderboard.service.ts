import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Score } from '../models/score.model'; // ✅ chemin à adapter selon ton arborescence

export interface Player {
  username: string;
  totalScore: number;
}

@Injectable({
  providedIn: 'root'
})
export class LeaderboardService {
  private apiUrl = 'http://localhost:80822/api/scores/leaderboard';

  constructor(private http: HttpClient) {}

  // Le backend renvoie { top: Player[], others: Player[] }
  getAll(): Observable<{ top: Player[]; others: Player[] }> {
    return this.http.get<{ top: Player[]; others: Player[] }>(this.apiUrl);
  }
}
