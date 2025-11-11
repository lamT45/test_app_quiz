// src/app/admin/manage-users/manage-users.component.ts
import { Component, OnInit } from '@angular/core';
import { AdminUserService, AdminUser } from '../services/admin-user.service';

@Component({
  selector: 'app-manage-users',
  templateUrl: './manage-users.component.html',
  styleUrls: ['./manage-users.component.scss']
})
export class ManageUsersComponent implements OnInit {
  users: AdminUser[] = [];
  loading = false;
  errorMessage = '';
  search = '';

  // création
  newUser: AdminUser = {
    username: '',
    email: '',
    password: '',
    role: 'PLAYER'
  };

  // édition inline
  editingId: number | null = null;
  editUser: Partial<AdminUser> = {};

  constructor(private userService: AdminUserService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.userService.getAll().subscribe({
      next: (data) => {
        this.users = data;
        this.loading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des utilisateurs';
        console.error(err);
        this.loading = false;
      }
    });
  }

  // filtre client simple
  get filteredUsers(): AdminUser[] {
    const q = this.search.trim().toLowerCase();
    if (!q) return this.users;
    return this.users.filter(u =>
      (u.username || '').toLowerCase().includes(q) ||
      (u.email || '').toLowerCase().includes(q) ||
      (u.role || '').toLowerCase().includes(q)
    );
  }

  // création
  add(): void {
    if (!this.newUser.username || !this.newUser.email) return;
    this.userService.create(this.newUser).subscribe({
      next: () => {
        this.newUser = { username: '', email: '', password: '', role: 'PLAYER' };
        this.load();
      },
      error: (e) => console.error(e)
    });
  }

  // passage en mode édition
  startEdit(u: AdminUser): void {
    this.editingId = u.id!;
    this.editUser = { username: u.username, email: u.email, role: u.role };
  }

  cancelEdit(): void {
    this.editingId = null;
    this.editUser = {};
  }

  // sauvegarde édition
  saveEdit(id: number): void {
    this.userService.update(id, this.editUser).subscribe({
      next: () => {
        this.cancelEdit();
        this.load();
      },
      error: (e) => console.error(e)
    });
  }

  // suppression (supprime aussi les scores côté backend)
  remove(id: number): void {
    if (!confirm('Supprimer cet utilisateur ? (ses scores seront aussi supprimés)')) return;
    this.userService.delete(id).subscribe({
      next: () => this.load(),
      error: (e) => console.error(e)
    });
  }
}
