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
  //  Redirection par défaut vers /home
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  //  Pages publiques
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'classement', component: LeaderboardComponent },
  { path: 'quiz', component: QuizListComponent },
  { path: 'quiz/:id', component: QuizDetailComponent },

  //  Pages protégées (accessibles seulement si connecté)
  { path: 'play/:id', component: QuizPlayComponent, canActivate: [AuthGuard] },


  //  Redirection pour routes inconnues
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
