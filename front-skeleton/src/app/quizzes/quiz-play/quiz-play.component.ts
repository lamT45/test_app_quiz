import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import * as confetti from 'canvas-confetti';
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
export class QuizPlayComponent implements OnInit, OnDestroy {
  quizId!: number;
  quiz: any; // ✅ pour stocker le quiz complet (et récupérer duration)
  questions: any[] = [];
  currentIndex = 0;
  score = 0;
  selectedChoice: string | null = null;
  showResult = false;
  animState = true;
  isCorrect: boolean | null = null;

  // ⏱️ Timer
  timeLeft = 0;
  maxTime = 0;
  timerInterval: any;

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
    this.loadQuiz(); // ✅ on charge le quiz (pour avoir la durée)
  }

  ngOnDestroy(): void {
    clearInterval(this.timerInterval);
  }

  // 📥 Charger le quiz complet (titre, durée, etc.)
  loadQuiz(): void {
    this.http.get<any>(`http://localhost:8082/api/quizzes/${this.quizId}`).subscribe({
      next: (quizData) => {
        this.quiz = quizData;
        this.maxTime = quizData.duration || 15; // par défaut 15s si non défini
        this.loadQuestions();
      },
      error: (err) => console.error('Erreur lors du chargement du quiz', err)
    });
  }

  // 📥 Charger les questions
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
        this.startTimer(); // démarrer le timer dès la première question
      },
      error: (err) => console.error('Erreur lors du chargement des questions', err)
    });
  }

  // 🔀 Mélange de tableau
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

  // ⏱️ Démarrer le timer
  startTimer(): void {
    clearInterval(this.timerInterval);
    this.timeLeft = this.maxTime;

    this.timerInterval = setInterval(() => {
      this.timeLeft--;

      if (this.timeLeft <= 0) {
        clearInterval(this.timerInterval);
        this.nextQuestion(true); // auto-passer si le temps est écoulé
      }
    }, 1000);
  }

  // ✅ Passer à la question suivante
  nextQuestion(autoSkipped = false): void {
    clearInterval(this.timerInterval);
    const currentQuestion = this.questions[this.currentIndex];
    let isAnswerCorrect = false;

    if (!autoSkipped) {
      isAnswerCorrect =
        this.selectedChoice?.trim().toLowerCase() ===
        currentQuestion.correctAnswer?.trim().toLowerCase();
    }

    this.isCorrect = isAnswerCorrect;

    if (isAnswerCorrect) {
      this.correctSound.play();

      // 🧮 Calcul des points dynamiques
      const timeBonus = this.timeLeft / this.maxTime; // ratio entre 0 et 1
      const earnedPoints = Math.round(currentQuestion.points * (0.5 + timeBonus)); // min 50% - max 150%
      this.score += earnedPoints;
    } else if (!autoSkipped) {
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
        } else {
          this.animState = true;
          this.startTimer(); // 🔁 redémarre le timer pour la question suivante
        }
      }, 300);
    }, 800);
  }

  getTimerClass(): string {
    const ratio = this.timeLeft / this.maxTime;
    if (ratio > 0.6) return 'timer-green';
    else if (ratio > 0.3) return 'timer-orange';
    else return 'timer-red';
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

  // 🔙 Retour
  goBack(): void {
    this.router.navigate(['/quiz']);
  }

  // 🎊 Confettis de victoire
  launchConfetti(): void {
    const duration = 3 * 1000;
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

  // 🧮 Total des points max
  getTotalPoints(): number {
    return this.questions.reduce((sum, q) => sum + (q.points ?? 0), 0);
  }
}
