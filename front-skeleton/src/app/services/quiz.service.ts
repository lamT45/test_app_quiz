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
        duration: 10,
        players: 1234,
        rating: 4.5,
        questions: Array(15)
      },
      {
        id: 2,
        title: 'Les Capitales du Monde',
        category: 'Géographie',
        description: 'Connaissez-vous toutes les capitales ? Prouvez-le !',
        level: 'Moyen',
        duration: 15,
        players: 856,
        rating: 4.7,
        questions: Array(20)
      },
      {
        id: 3,
        title: 'Histoire de France',
        category: 'Histoire',
        description: 'De la Gaule à nos jours, testez vos connaissances historiques',
        level: 'Difficile',
        duration: 20,
        players: 543,
        rating: 4.8,
        questions: Array(25)
      },
      {
        id: 4,
        title: 'Sciences et Technologies',
        category: 'Sciences',
        description: 'Physique, chimie, biologie... êtes-vous un scientifique ?',
        level: 'Moyen',
        duration: 15,
        players: 978,
        rating: 4.6,
        questions: Array(18)
      },
      {
        id: 5,
        title: 'Cinéma et Séries TV',
        category: 'Divertissement',
        description: 'Êtes-vous un vrai cinéphile ? Testez vos connaissances !',
        level: 'Facile',
        duration: 15,
        players: 1567,
        rating: 4.9,
        questions: Array(22)
      },
      {
        id: 6,
        title: 'Sport - Champions et Records',
        category: 'Sport',
        description: 'Connaissez-vous les plus grands exploits sportifs ?',
        level: 'Moyen',
        duration: 20,
        players: 876,
        rating: 4.4,
        questions: Array(20)
      }
    ];
    return of(quizzes);
  }
}
