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
  role = 'player'; // ✅ par défaut, joueur
  errorMessage = '';

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    // Appel au backend pour vérifier les identifiants
    this.authService.login({ username: this.username, password: this.password }).subscribe({
      next: (res: any) => {
        localStorage.setItem('token', res.token);
        this.authService.setCurrentUser(res.user);

        // 🔹 Logique de redirection selon le rôle choisi
        if (this.role === 'admin') {
          localStorage.setItem('adminToken', res.token);
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/home']);
        }
      },
      error: (err) => {
        this.errorMessage = err.error?.message || 'Nom d’utilisateur ou mot de passe invalide.';
      }
    });
  }
}
