import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { QuizListComponent } from './quizzes/quiz-list/quiz-list.component';
import { LeaderboardComponent } from './scores/leaderboard/leaderboard.component';
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'quiz', component: QuizListComponent },
  { path: 'classement', component: LeaderboardComponent },
  { path: 'quiz/:id', component: QuizDetailComponent },
  { path: 'play/:id', component: QuizPlayComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
