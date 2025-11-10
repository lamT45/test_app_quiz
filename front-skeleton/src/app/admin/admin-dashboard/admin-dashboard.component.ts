import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss']
})
export class AdminDashboardComponent {
  constructor(private router: Router) {}

  logout() {
    // ✅ Supprime le token admin
    localStorage.removeItem('adminToken');

    // ✅ Redirige vers la page de connexion admin
    this.router.navigate(['/login-admin']);
  }
}
