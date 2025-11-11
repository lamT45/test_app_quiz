// src/app/admin/services/admin-user.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AdminUser {
  id?: number;
  username: string;
  email: string;
  password?: string;   // optionnel pour création
  role: 'ADMIN' | 'PLAYER';
  createdAt?: string;
}

@Injectable({ providedIn: 'root' })
export class AdminUserService {
  private baseUrl = 'http://localhost:8082/api/admin/users';

  constructor(private http: HttpClient) {}

  getAll(): Observable<AdminUser[]> {
    return this.http.get<AdminUser[]>(this.baseUrl);
  }

  getById(id: number): Observable<AdminUser> {
    return this.http.get<AdminUser>(`${this.baseUrl}/${id}`);
  }

  create(user: AdminUser): Observable<AdminUser> {
    return this.http.post<AdminUser>(this.baseUrl, user);
  }

  update(id: number, user: Partial<AdminUser>): Observable<AdminUser> {
    return this.http.put<AdminUser>(`${this.baseUrl}/${id}`, user);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
