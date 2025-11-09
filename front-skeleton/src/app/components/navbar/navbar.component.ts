import { Component, HostListener, OnInit  } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user.model';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent  implements OnInit {
  menuOpen = false;
  userMenuOpen = false;
  isLoggedIn = false;
  user: User | null = null;
  isScrolled = false;

  constructor(private authService: AuthService) {}

  ngOnInit() {
    //  Observe les changements d’état de connexion
    this.authService.currentUser$.subscribe(user => {
      this.isLoggedIn = !!user;
      this.user = user;
    });

    //  Initialise si un token existe déjà
    this.isLoggedIn = this.authService.isAuthenticated();
    const savedUser = localStorage.getItem('user');
    if (savedUser) this.user = JSON.parse(savedUser);
  }

  toggleMenu() {
    this.menuOpen = !this.menuOpen;
  }

  toggleUserMenu() {
    this.userMenuOpen = !this.userMenuOpen;
  }

  closeMenu() {
    this.menuOpen = false;
  }

  logout() {
    this.authService.logout();
    this.user = null;
    this.isLoggedIn = false;
    this.userMenuOpen = false;
  }

  @HostListener('window:scroll', [])
  onScroll() {
    this.isScrolled = window.scrollY > 10;
  }
}
