import { Component, OnInit } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.scss"],
})
export class HomeComponent implements OnInit {
  totalQuizzes = 0;
  totalUsers = 0;
  categories: string[] = [];


  private apiUrl = 'http://localhost:8082/api';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.loadStats();
    this.loadCategories();
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

  loadCategories(): void {
    this.http.get<string[]>(`${this.apiUrl}/quizzes/categories`)
      .subscribe({
        next: data => this.categories = data,
        error: err => console.error('Erreur lors du chargement des catégories', err)
      });
  }

  getCategoryColor(category: string): string {
    switch (category) {
      case 'Culture Générale': return '#EAB308'; // jaune
      case 'Géographie': return '#22C55E'; // vert
      case 'Histoire': return '#F97316'; // orange
      case 'Sciences': return '#3B82F6'; // bleu
      case 'Divertissement': return '#A855F7'; // violet
      case 'Sport': return '#EF4444'; // rouge
      default: return '#8B5CF6'; // violet par défaut
    }
  }
  goToCategory(category: string): void {
    this.router.navigate(['/quiz'], { queryParams: { category } });
  }
}
