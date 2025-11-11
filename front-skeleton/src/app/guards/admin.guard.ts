import { Injectable } from '@angular/core';
import { CanActivate, Router, UrlTree } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): boolean | UrlTree {
    const user = this.authService.getCurrentUser();

    //  Vérifie si l’utilisateur est connecté et a le rôle ADMIN
    if (user && user.role && user.role.toUpperCase() === 'ADMIN') {
      return true;
    }

    console.warn('🚫 Accès refusé — réservé aux administrateurs');
    return this.router.parseUrl('/home'); // redirige les non-admins vers /home
  }
}
