import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private apiUrl = 'http://localhost:8082/api/auth';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  private redirectUrl: string | null = null;

  constructor(private http: HttpClient) {
    const token = localStorage.getItem('token');
    if (token) {
      const savedUser = localStorage.getItem('user');
      if (savedUser) {
        this.currentUserSubject.next(JSON.parse(savedUser));
      }
    }
  }

  // === AUTHENTIFICATION ===
  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  setCurrentUser(user: User) {
    this.currentUserSubject.next(user);
    localStorage.setItem('user', JSON.stringify(user));
  }

  //  Retourne directement le user (pas le BehaviorSubject)
  getCurrentUser(): User | null {
    const user = this.currentUserSubject.value;
    if (user) return user;

    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const parsedUser = JSON.parse(storedUser);
      this.currentUserSubject.next(parsedUser);
      return parsedUser;
    }
    return null;
  }

  register(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    localStorage.removeItem('userId');
    this.currentUserSubject.next(null);
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('token');
  }

  // Gestion de redirection après login
  setRedirectUrl(url: string) {
    this.redirectUrl = url;
    localStorage.setItem('redirectUrl', url);
  }

  getRedirectUrl(): string | null {
    return this.redirectUrl || localStorage.getItem('redirectUrl');
  }

  clearRedirectUrl() {
    this.redirectUrl = null;
    localStorage.removeItem('redirectUrl');
  }
}
