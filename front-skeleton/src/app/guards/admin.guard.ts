import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private router: Router) {}

  canActivate(): boolean {
    const isAdminLoggedIn = !!localStorage.getItem('adminToken');

    if (!isAdminLoggedIn) {
      this.router.navigate(['/login-admin']);
      return false;
    }

    return true;
  }
}
