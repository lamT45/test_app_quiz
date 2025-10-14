import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  totalQuizzes = 0;
  totalUsers = 0;

  private apiUrl = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadStats();
  }

  loadStats(): void {
    this.http.get<any[]>(`${this.apiUrl}/quizzes`)
      .subscribe({
        next: data => this.totalQuizzes = data.length,
        error: err => console.error('Erreur lors du chargement des quiz', err)
      });

    this.http.get<any[]>(`${this.apiUrl}/users`)
      .subscribe({
        next: data => this.totalUsers = data.length,
        error: err => console.error('Erreur lors du chargement des utilisateurs', err)
      });
  }
}
