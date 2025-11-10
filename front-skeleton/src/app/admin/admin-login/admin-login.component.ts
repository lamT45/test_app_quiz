import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private router: Router) {}

  loginAdmin() {
    // 🟣 Identifiants de démo
    if (this.email === 'admin@quiz.com' && this.password === 'admin123') {
      localStorage.setItem('adminToken', 'true'); // token fictif
      this.router.navigate(['/admin']);
    } else {
      this.errorMessage = 'Identifiants invalides';
    }
  }
}
