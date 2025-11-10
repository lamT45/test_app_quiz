import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

// 🧩 Composants Admin
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageQuizzesComponent } from './manage-quizzes/manage-quizzes.component';
import { ManageQuestionsComponent } from './manage-questions/manage-questions.component';

// 🛣️ Routing
import { AdminRoutingModule } from './admin-routing.module';

@NgModule({
  declarations: [
    AdminDashboardComponent,
    ManageUsersComponent,
    ManageQuizzesComponent,
    ManageQuestionsComponent
  ],
  imports: [
    CommonModule,           // ✅ ngIf, ngFor, pipes, etc.
    FormsModule,            // ✅ ngModel
    ReactiveFormsModule,    // ✅ pour formulaires avancés
    RouterModule,
    AdminRoutingModule
  ]
})
export class AdminModule {}
