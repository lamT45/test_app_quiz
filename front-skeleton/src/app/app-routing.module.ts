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
import { AdminGuard } from './guards/admin.guard';

const routes: Routes = [
  // 🏠 Redirection par défaut
  { path: '', redirectTo: '/home', pathMatch: 'full' },

  // 🌍 Pages publiques
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'home', component: HomeComponent },
  { path: 'classement', component: LeaderboardComponent },
  { path: 'quiz', component: QuizListComponent },
  { path: 'quiz/:id', component: QuizDetailComponent },

  //  Zones protégées (auth obligatoire)
  { path: 'play/:id', component: QuizPlayComponent, canActivate: [AuthGuard] },

  // 👑 Panneau Admin (chargement paresseux + guard)
  {
    path: 'admin',
    canActivate: [AdminGuard],
    loadChildren: () =>
      import('./admin/admin.module').then((m) => m.AdminModule),
  },

  //  Fallback pour routes inconnues
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
