import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { QuizService } from 'services/quiz.service';
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
  quiz: any;
  questions: any[] = [];
  currentIndex = 0;
  selectedChoice: string | null = null;
  showResult = false;
  animState = true;
  isCorrect: boolean | null = null;
  score: number | null = null;

  // Temps total et par question
  timeLeft = 0;
  maxTime = 0;
  timerInterval: any;
  totalTimeTaken = 0;

  // Sons
  clickSound = new Audio('assets/sounds/click.mp3');
  correctSound = new Audio('assets/sounds/correct.mp3');
  wrongSound = new Audio('assets/sounds/wrong.mp3');
  victorySound = new Audio('assets/sounds/victory.mp3');

  // Réponses de l’utilisateur
  userAnswers: string[] = [];
  // Verrouillage des réponses pour éviter les clics multiples
  isAnswerLocked = false;
// Feedback de la réponse
  showAnswerFeedback = false;


  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private http: HttpClient,
    private quizService: QuizService

  ) {}

  ngOnInit(): void {
    this.quizId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadQuiz();
  }

  ngOnDestroy(): void {
    clearInterval(this.timerInterval);
  }

  // Charger le quiz
  loadQuiz(): void {
    this.http.get<any>(`http://localhost:8082/api/quizzes/${this.quizId}`).subscribe({
      next: (quizData) => {
        this.quiz = quizData;
        this.maxTime = quizData.duration || 15;
        this.loadQuestions();
      },
      error: (err) => console.error('Erreur lors du chargement du quiz', err)
    });
  }

  // Charger les questions
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
        this.startTimer();
      },
      error: (err) => console.error('Erreur lors du chargement des questions', err)
    });
  }

  // Mélanger un tableau
  private shuffleArray(array: any[]): any[] {
    return array
      .map(value => ({ value, sort: Math.random() }))
      .sort((a, b) => a.sort - b.sort)
      .map(({ value }) => value);
  }

  // Sélection d’un choix
  selectChoice(choice: string): void {
    this.selectedChoice = choice;
    this.clickSound.play();
  }

  // Timer
  startTimer(): void {
    clearInterval(this.timerInterval);
    this.timeLeft = this.maxTime;

    this.timerInterval = setInterval(() => {
      this.timeLeft--;
      this.totalTimeTaken++;

      if (this.timeLeft <= 0) {
        clearInterval(this.timerInterval);
        this.nextQuestion(true);
      }
    }, 1000);
  }

  // Passer à la question suivante
  nextQuestion(autoSkipped = false): void {
    clearInterval(this.timerInterval);
    const currentQuestion = this.questions[this.currentIndex];
    this.isAnswerLocked = true;
    this.userAnswers.push(this.selectedChoice || "");

    if (!autoSkipped && this.selectedChoice) {
      const correctAnswer = currentQuestion.correctAnswer?.trim().toLowerCase();
      const userAnswer = this.selectedChoice?.trim().toLowerCase();

      this.showAnswerFeedback = true; //  Montre feedback uniquement après validation

      if (userAnswer === correctAnswer) {
        this.isCorrect = true;
        this.correctSound.play();
      } else {
        this.isCorrect = false;
        this.wrongSound.play();
      }
    }

    // Pause avant de passer à la suivante
    setTimeout(() => {
      this.showAnswerFeedback = false; //  Cache feedback pour la prochaine
      this.isCorrect = null;
      this.isAnswerLocked = false;
      this.selectedChoice = null;
      this.animState = false;

      setTimeout(() => {
        this.currentIndex++;
        if (this.currentIndex >= this.questions.length) {
          this.showResult = true;
          this.victorySound.play();
          this.launchConfetti();
          this.saveScoreToBackend();
        } else {
          this.animState = true;
          this.startTimer();
        }
      }, 500);
    }, 1000);
  }

  // Enregistrer le score (backend fait le calcul)
  saveScoreToBackend(): void {
    const payload = {
      userId: Number(localStorage.getItem('userId')),
      quizId: this.quizId,
      timeTakenSeconds: this.totalTimeTaken,
      answers: this.userAnswers
    };

    this.http.post<any>('http://localhost:8082/api/scores/calculate', payload).subscribe({
      next: (res) => {
        console.log('✅ Score calculé et enregistré par le backend', res);
        this.score = res.score_obtained; // 🔹 affiche le score du backend dans le template
      },
      error: (err) => console.error('❌ Erreur lors de l’enregistrement du score', err)
    });
  }


  getTimerClass() {
    if (this.timeLeft <= 3) return 'urgent final-seconds';
    if (this.timeLeft <= 10) return 'urgent';
    return '';

  }


  restartQuiz(): void {
    this.currentIndex = 0;
    this.showResult = false;
    this.animState = true;
    this.isCorrect = null;
    this.userAnswers = [];
    this.totalTimeTaken = 0;
    this.loadQuestions();
  }

  goBack(): void {
    this.router.navigate(['/quiz']);
  }

  // Effet de victoire
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

  rateQuiz(value: number) {
    this.quizService.rateQuiz(this.quiz.id, value).subscribe({
      next: (updatedQuiz) => {
        this.quiz.rating = updatedQuiz.rating;
        this.quiz.ratingCount = updatedQuiz.ratingCount;

        // Animation confettis + glow subtil
        const duration = 1000;
        const end = Date.now() + duration;
        const canvas = document.getElementById('confetti-canvas') as HTMLCanvasElement;
        const confettiInstance = confetti.create(canvas, { resize: true, useWorker: true });

        (function frame() {
          confettiInstance({
            particleCount: 3,
            startVelocity: 15,
            spread: 100,
            ticks: 60,
            origin: { y: 0.7 },
            colors: ['#facc15', '#a855f7', '#7c3aed']
          });
          if (Date.now() < end) requestAnimationFrame(frame);
        })();

        // Notification légère
        alert(`⭐ Merci pour votre note de ${value} étoiles !`);
      },
      error: () => alert("Erreur lors de l'envoi de votre note.")
    });
  }
  getRoundedRating(): number {
    return Math.round(this.quiz?.rating || 0);
  }
}
