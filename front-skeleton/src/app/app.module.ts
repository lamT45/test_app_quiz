// Module principal de l'application Angular
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

// Angular Common Module
import { CommonModule } from '@angular/common';


// Composants principaux
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';

// Quiz
import { QuizDetailComponent } from './quizzes/quiz-detail/quiz-detail.component';
import { QuizPlayComponent } from './quizzes/quiz-play/quiz-play.component';
import { QuizListComponent } from "./quizzes/quiz-list/quiz-list.component";

// Scores
import { LeaderboardComponent } from "./scores/leaderboard/leaderboard.component";


// Authentification
import { LoginComponent } from './components/auth/login/login.component';
import { RegisterComponent } from './components/auth/register/register.component';

// Services
import { QuizService } from './services/quiz.service';
import { AuthService } from './services/auth.service';
import { ScoreService } from './services/score.service';
import { QuestionService } from './services/question.service';




// Routing
import { AppRoutingModule } from './app-routing.module';


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
  ],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule
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
