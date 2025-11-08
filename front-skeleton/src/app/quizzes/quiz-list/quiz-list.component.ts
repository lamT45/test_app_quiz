import { Component, OnInit } from '@angular/core';
import { QuizService } from '../../services/quiz.service';
import { Quiz } from '../../models/quiz.model';

@Component({
  selector: 'app-quiz-list',
  templateUrl: './quiz-list.component.html',
  styleUrls: ['./quiz-list.component.scss']
})
export class QuizListComponent implements OnInit {
  quizzes: Quiz[] = [];

  constructor(private quizService: QuizService) {}

  ngOnInit(): void {
    this.quizService.getAll().subscribe((data) => this.quizzes = data);
  }
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

  getLevelColor(level: string): string {
    switch (level) {
      case 'Facile': return '#22C55E';
      case 'Moyen': return '#FACC15';
      case 'Difficile': return '#EF4444';
      default: return '#A855F7';
    }
  }

}
