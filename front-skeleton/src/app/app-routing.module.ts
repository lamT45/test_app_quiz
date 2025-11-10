import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// 🏠 Pages principales
import { HomeComponent } from './components/home/home.component';

// 🎯 Quiz
import { QuizListComponent } from './quizzes/quiz-list/quiz-list.component';
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';

// 🏆 Classement
import { LeaderboardComponent } from './scores/leaderboard/leaderboard.component';

// 🔐 Authentification utilisateur
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';

// 🔐 Authentification admin
import { AdminLoginComponent } from './admin/admin-login/admin-login.component';

// 🛡️ Guards
import { AuthGuard } from './guards/auth.guards';
import { AdminGuard } from './guards/admin.guard';

const routes: Routes = [
  // 🏠 Page d'accueil
  { path: '', component: HomeComponent },

  // 🔑 Authentification utilisateur
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // 🎯 Quiz
  { path: 'quiz', component: QuizListComponent },
  { path: 'quiz/:id', component: QuizDetailComponent },
  { path: 'play/:id', component: QuizPlayComponent, canActivate: [AuthGuard] },

  // 🏆 Classement
  { path: 'classement', component: LeaderboardComponent },

  // 🔐 Connexion administrateur
  { path: 'login-admin', component: AdminLoginComponent },

  // ⚙️ Section Administration (Lazy Loading + Guard)
  {
    path: 'admin',
    loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AdminGuard]
  },

  // 🚫 Redirection pour routes inconnues
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
