import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  username = '';
  password = '';
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (res: any) => {
        // 🔹 Enregistre le token si présent
        if (res.token) {
          localStorage.setItem('token', res.token);
        }

        // 🔹 Enregistre les infos utilisateur
        if (res.user) {
          this.authService.setCurrentUser(res.user);
          localStorage.setItem('userId', res.user.id);
          localStorage.setItem('user', JSON.stringify(res.user));
        }

        // 🔹 Vérifie le rôle de l’utilisateur
        const userRole = res.user?.role?.toUpperCase();

        if (userRole === 'ADMIN') {
          console.log(' Connexion Admin détectée — redirection vers le panneau /admin');
          this.router.navigate(['/admin']);
          return;
        }

        // 🔹 Sinon (PLAYER ou autre) — redirection classique
        const redirectUrl = this.authService.getRedirectUrl();
        if (redirectUrl) {
          console.log('️ Redirection vers', redirectUrl);
          this.router.navigateByUrl(redirectUrl);
          this.authService.clearRedirectUrl();
        } else {
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        this.errorMessage = err.error.message || 'Username ou mot de passe invalide';
      }
    });
  }
}
