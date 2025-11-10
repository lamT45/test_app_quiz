import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// 🏠 Composants principaux
import { HomeComponent } from './components/home/home.component';

// 🎯 Quiz
import { QuizListComponent } from './quizzes/quiz-list/quiz-list.component';
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';

// 🏆 Scores
import { LeaderboardComponent } from './scores/leaderboard/leaderboard.component';

// 🔐 Auth
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';

// 🧱 (Facultatif) Guard pour protéger l'accès admin
import { AdminGuard } from './guards/admin.guard';

const routes: Routes = [
  // 🏠 Accueil
  { path: '', component: HomeComponent },

  // 🎯 Quiz
  { path: 'quiz', component: QuizListComponent },
  { path: 'quiz/:id', component: QuizDetailComponent },
  { path: 'play/:id', component: QuizPlayComponent },

  // 🏆 Classement
  { path: 'leaderboard', component: LeaderboardComponent },

  // 🔐 Authentification
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // ⚙️ Section Admin (chargée dynamiquement)
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AdminGuard] // 🔒 Facultatif (à garder si tu veux restreindre l’accès)
  },

  // 🚫 Redirection si URL inconnue
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
