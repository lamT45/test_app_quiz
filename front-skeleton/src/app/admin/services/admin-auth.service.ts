import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminAuthService {

  private readonly ADMIN_EMAIL = 'admin@quiz.com';
  private readonly ADMIN_PASSWORD = 'admin123';

  constructor(private router: Router) {}

  login(email: string, password: string): boolean {
    if (email === this.ADMIN_EMAIL && password === this.ADMIN_PASSWORD) {
      localStorage.setItem('role', 'admin');
      return true;
    }
    return false;
  }

  logout(): void {
    localStorage.removeItem('role');
    this.router.navigate(['/login-admin']);
  }

  isAdminLoggedIn(): boolean {
    return localStorage.getItem('role') === 'admin';
  }
}
