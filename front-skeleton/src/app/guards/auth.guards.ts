import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(private router: Router, private authService: AuthService) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const userId = localStorage.getItem('userId');
    const token = localStorage.getItem('token');

    if (!userId && !token) {
      console.log('🔒 Non connecté — redirection vers /login depuis', state.url);
      this.authService.setRedirectUrl(state.url);
      console.log('📍 Sauvegarde redirectUrl =', state.url);
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}
