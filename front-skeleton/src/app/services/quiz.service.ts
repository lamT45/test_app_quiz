import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Quiz } from '../models/quiz.model';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor() {}
  getById(id: number): Observable<Quiz | undefined> {
    return new Observable<Quiz | undefined>((observer) => {
      this.getAll().subscribe({
        next: (quizzes) => {
          const quiz = quizzes.find(q => q.id === id);
          observer.next(quiz);
          observer.complete();
        },
        error: (err) => {
          observer.error(err);
        }
      });
    });
  }

  getAll(): Observable<Quiz[]> {
    const quizzes: Quiz[] = [
      {
        id: 1,
        title: 'Culture Générale - Niveau Facile',
        category: 'Culture Générale',
        description: 'Testez vos connaissances générales avec ce quiz pour débutants',
        level: 'Facile',
        players: 1234,
        questions: Array(15),
        duration: 45
      },
      {
        id: 2,
        title: 'Les Capitales du Monde',
        category: 'Géographie',
        description: 'Connaissez-vous toutes les capitales ? Prouvez-le !',
        level: 'Moyen',
        players: 856,
        questions: Array(20),
        duration: 30
      },
      {
        id: 3,
        title: 'Histoire de France',
        category: 'Histoire',
        description: 'De la Gaule à nos jours, testez vos connaissances historiques',
        level: 'Difficile',
        players: 543,
        questions: Array(25),
        duration: 15
      },
      {
        id: 4,
        title: 'Sciences et Technologies',
        category: 'Sciences',
        description: 'Physique, chimie, biologie... êtes-vous un scientifique ?',
        level: 'Moyen',
        players: 978,
        questions: Array(18),
        duration: 30
      },
      {
        id: 5,
        title: 'Cinéma et Séries TV',
        category: 'Divertissement',
        description: 'Êtes-vous un vrai cinéphile ? Testez vos connaissances !',
        level: 'Facile',
        players: 1567,
        questions: Array(22),
        duration: 45
      },
      {
        id: 6,
        title: 'Sport - Champions et Records',
        category: 'Sport',
        description: 'Connaissez-vous les plus grands exploits sportifs ?',
        level: 'Moyen',
        players: 876,
        questions: Array(20),
        duration: 30
      }
    ];
    return of(quizzes);
  }
}
