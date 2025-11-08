import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { QuizListComponent } from './quizzes/quiz-list/quiz-list.component';
import { LeaderboardComponent } from './scores/leaderboard/leaderboard.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';
import { AuthGuard } from './guards/auth.guards';

const routes: Routes = [
  // ✅ Redirection par défaut vers /login
  { path: '', redirectTo: '/login', pathMatch: 'full' },

  // ✅ Pages publiques
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },

  // ✅ Pages protégées (accessibles seulement si connecté)
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'quiz', component: QuizListComponent, canActivate: [AuthGuard] },
  { path: 'classement', component: LeaderboardComponent, canActivate: [AuthGuard] },
  { path: 'quiz/:id', component: QuizDetailComponent, canActivate: [AuthGuard] },
  { path: 'play/:id', component: QuizPlayComponent, canActivate: [AuthGuard] },

  // ✅ Redirection pour routes inconnues
  { path: '**', redirectTo: '/login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
