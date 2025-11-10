import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AdminAuthService } from '../services/admin-auth.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.scss']
})
export class AdminLoginComponent {
  email = '';
  password = '';
  errorMsg = '';

  constructor(private adminAuth: AdminAuthService, private router: Router) {}

  onLogin(): void {
    const success = this.adminAuth.login(this.email, this.password);

    if (success) {
      this.router.navigate(['/admin']);
    } else {
      this.errorMsg = 'Identifiants invalides. Veuillez réessayer.';
    }
  }
}
