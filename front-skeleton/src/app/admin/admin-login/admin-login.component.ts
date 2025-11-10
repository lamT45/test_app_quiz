import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-login',
  standalone: true, // ✅ composant standalone (pas besoin d’être dans un module)
  imports: [CommonModule, FormsModule], // ✅ pour ngModel, ngIf...
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private router: Router) {}

  loginAdmin() {
    // 🟣 Identifiants fictifs de démo
    if (this.email.trim() === 'admin@quiz.com' && this.password === 'admin123') {
      localStorage.setItem('adminToken', 'true'); // ✅ enregistre le token admin
      this.router.navigate(['/admin']); // ✅ redirection vers le dashboard admin
    } else {
      this.errorMessage = 'Identifiants invalides ❌';
    }
  }
}
