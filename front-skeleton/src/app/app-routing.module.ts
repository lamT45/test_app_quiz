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

// 🔐 Auth utilisateurs
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';

// 🔐 Auth admin
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';

// 🛡️ Guards
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

  // 🔐 Auth utilisateur
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // 🔐 Auth admin
  {
    path: 'login-admin',
    loadComponent: () =>
      import('./admin/admin-login/admin-login.component')
        .then(m => m.AdminLoginComponent)
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./admin/admin.module').then(m => m.AdminModule)
  }
,

  // 🚫 Redirection pour routes inconnues
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
