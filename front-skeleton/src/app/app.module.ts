import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// 🏠 Composants classiques
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';

// 🎯 Quiz
import { QuizListComponent } from './quizzes/quiz-list/quiz-list.component';
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';

// 🏆 Classement (standalone)
import { LeaderboardComponent } from './scores/leaderboard/leaderboard.component';

// 🔐 Auth
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';

// ⚙️ Routing principal
import { AppRoutingModule } from './app-routing.module';

// 🧩 Services
import { QuizService } from './services/quiz.service';
import { AuthService } from './services/auth.service';
import { ScoreService } from './services/score.service';
import { QuestionService } from './services/question.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    QuizListComponent,
    QuizDetailComponent,
    QuizPlayComponent,
    LoginComponent,
    RegisterComponent
    // ❌ PAS AdminLoginComponent ici
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    LeaderboardComponent // ✅ car standalone
  ],
  providers: [
    QuizService,
    AuthService,
    ScoreService,
    QuestionService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
