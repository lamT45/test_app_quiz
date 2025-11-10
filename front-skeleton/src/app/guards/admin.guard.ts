import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AdminAuthService } from '../admin/services/admin-auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private adminAuth: AdminAuthService, private router: Router) {}

  canActivate(): boolean {
    if (!this.adminAuth.isAdminLoggedIn()) {
      this.router.navigate(['/login-admin']);
      return false;
    }
    return true;
  }
}
