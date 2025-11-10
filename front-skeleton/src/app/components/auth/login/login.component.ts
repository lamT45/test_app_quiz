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
        //  Enregistre le token si présent
        if (res.token) {
          localStorage.setItem('token', res.token);
        }

        //  Enregistre les infos utilisateur
        if (res.user) {
          this.authService.setCurrentUser(res.user);
          localStorage.setItem('userId', res.user.id); //  obligatoire pour AuthGuard
          localStorage.setItem('user', JSON.stringify(res.user));
        }

        //  Redirige vers la dernière page visitée
        const redirectUrl = this.authService.getRedirectUrl();
        if (redirectUrl) {
          console.log(' Redirection vers', redirectUrl);
          this.router.navigateByUrl(redirectUrl);
          this.authService.clearRedirectUrl(); // nettoyage
        } else {
          this.router.navigate(['/home']); // fallback par défaut
        }
      },
      error: (err) => {
        this.errorMessage = err.error.message || 'Username ou mot de passe invalide';
      }
    });
  }
}
