import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { QuizService } from '../../services/quiz.service';
import { Quiz } from '../../models/quiz.model';

@Component({
  selector: 'app-quiz-list',
  templateUrl: './quiz-list.component.html',
  styleUrls: ['./quiz-list.component.scss']
})
export class QuizListComponent implements OnInit {
  quizzes: Quiz[] = [];
  filteredQuizzes: Quiz[] = [];
  selectedCategory: string | null = null;


  constructor(
    private quizService: QuizService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // 🔹 Récupère tous les quiz depuis le backend
    this.quizService.getAll().subscribe((data) => {
      this.quizzes = data;

      // 🔹 Vérifie s’il y a un paramètre de catégorie dans l’URL
      this.route.queryParams.subscribe(params => {
        this.selectedCategory = params['category'] || null;

        if (this.selectedCategory) {
          //  Filtrer les quiz par catégorie
          this.filteredQuizzes = this.quizzes.filter(
            q => q.category === this.selectedCategory
          );
        } else {
          //  Sinon, afficher tous les quiz
          this.filteredQuizzes = this.quizzes;
        }
      });
    });
  }

  // Réinitialiser le filtre et revenir à tous les quiz
  resetFilter(): void {
    this.selectedCategory = null;
    this.filteredQuizzes = this.quizzes;
    this.router.navigate(['/quiz']); // retire le paramètre de l’URL
  }

  //  Couleurs de catégories
  getCategoryColor(category: string): string {
    switch (category) {
      case 'Culture Générale': return '#EAB308'; // jaune
      case 'Géographie': return '#22C55E'; // vert
      case 'Histoire': return '#F97316'; // orange
      case 'Sciences': return '#3B82F6'; // bleu
      case 'Divertissement': return '#A855F7'; // violet
      case 'Sport': return '#EF4444'; // rouge
      default: return '#8B5CF6'; // fallback violet
    }
  }


  // 🚀 Lancement du quiz
  startQuiz(quizId: number) {
    this.quizService.incrementPlayers(quizId).subscribe({
      next: (updatedQuiz) => {
        const index = this.quizzes.findIndex(q => q.id === quizId);
        if (index !== -1) {
          this.quizzes[index].players = updatedQuiz.players;

          // 💫 Animation pulse temporaire
          const playerCountEl = document.querySelectorAll('.player-count')[index];
          playerCountEl?.classList.add('pulsing');
          setTimeout(() => playerCountEl?.classList.remove('pulsing'), 600);
        }

        this.router.navigate(['/quiz', quizId]);
      },
      error: (err) => console.error('Erreur lors de la mise à jour des joueurs', err)
    });
  }
}
