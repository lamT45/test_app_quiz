import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import * as confetti from 'canvas-confetti'; // 🎊 confettis
import {
  trigger,
  transition,
  style,
  animate,
  group
} from '@angular/animations';

@Component({
  selector: 'app-quiz-play',
  templateUrl: './quiz-play.component.html',
  styleUrls: ['./quiz-play.component.scss'],
  animations: [
    trigger('questionAnimation', [
      transition(':enter', [
        style({ opacity: 0, transform: 'translateY(15px)' }),
        animate('400ms ease-out', style({ opacity: 1, transform: 'translateY(0)' }))
      ]),
      transition(':leave', [
        group([
          animate('300ms ease-in', style({ opacity: 0, transform: 'translateY(-10px)' }))
        ])
      ])
    ])
  ]
})
export class QuizPlayComponent implements OnInit {
  quizId!: number;
  questions: any[] = [];
  currentIndex = 0;
  score = 0;
  selectedChoice: string | null = null;
  showResult = false;
  animState = true;
  isCorrect: boolean | null = null;

  // 🔊 Sons
  clickSound = new Audio('assets/sounds/click.mp3');
  correctSound = new Audio('assets/sounds/correct.mp3');
  wrongSound = new Audio('assets/sounds/wrong.mp3');
  victorySound = new Audio('assets/sounds/victory.mp3');

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.quizId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadQuestions();
  }

  // 📥 Charger les questions depuis l’API et mélanger l’ordre
  loadQuestions(): void {
    this.http.get<any[]>(`http://localhost:8082/api/quizzes/${this.quizId}/questions`).subscribe({
      next: (data) => {
        this.questions = this.shuffleArray(
          data.map(q => ({
            ...q,
            choices: this.shuffleArray(
              [q.choice1, q.choice2, q.choice3, q.choice4].filter(Boolean)
            )
          }))
        );
      },
      error: (err) => console.error('Erreur lors du chargement des questions', err)
    });
  }

  // 🔀 Fonction pour mélanger un tableau
  private shuffleArray(array: any[]): any[] {
    return array
      .map(value => ({ value, sort: Math.random() }))
      .sort((a, b) => a.sort - b.sort)
      .map(({ value }) => value);
  }

  // 🎯 Sélection d’un choix
  selectChoice(choice: string): void {
    this.selectedChoice = choice;
    this.clickSound.play();
  }

  // ✅ Passer à la question suivante
  nextQuestion(): void {
    const currentQuestion = this.questions[this.currentIndex];
    const isAnswerCorrect =
      this.selectedChoice?.trim().toLowerCase() ===
      currentQuestion.correctAnswer?.trim().toLowerCase();

    this.isCorrect = isAnswerCorrect;

    if (isAnswerCorrect) {
      this.correctSound.play();
      this.score += currentQuestion.points ?? 1;
    } else {
      this.wrongSound.play();
    }

    setTimeout(() => {
      this.isCorrect = null;
      this.selectedChoice = null;
      this.animState = false;

      setTimeout(() => {
        this.currentIndex++;
        if (this.currentIndex >= this.questions.length) {
          this.showResult = true;
          this.victorySound.play();
          this.launchConfetti();
        }
        this.animState = true;
      }, 300);
    }, 800);
  }

  // 🔁 Rejouer le quiz
  restartQuiz(): void {
    this.currentIndex = 0;
    this.score = 0;
    this.showResult = false;
    this.animState = true;
    this.isCorrect = null;
    this.loadQuestions();
  }

  // 🔙 Retour à la page des quiz
  goBack(): void {
    this.router.navigate(['/quizzes']);
  }

  // 🎊 Confettis de victoire
  launchConfetti(): void {
    const duration = 3 * 1000; // 3 secondes
    const end = Date.now() + duration;

    const canvas = document.getElementById('confetti-canvas') as HTMLCanvasElement;
    const confettiInstance = confetti.create(canvas, { resize: true, useWorker: true });

    (function frame() {
      confettiInstance({
        particleCount: 5,
        startVelocity: 30,
        spread: 360,
        ticks: 60,
        origin: { y: 0.6 }
      });

      if (Date.now() < end) {
        requestAnimationFrame(frame);
      }
    })();
  }

  // 🧮 Calcul du total des points
  getTotalPoints(): number {
    return this.questions.reduce((sum, q) => sum + (q.points ?? 0), 0);
  }
}
