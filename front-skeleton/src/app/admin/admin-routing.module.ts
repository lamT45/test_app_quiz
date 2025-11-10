import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

// 🧱 Import des composants Admin
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';
import { ManageQuizzesComponent } from './manage-quizzes/manage-quizzes.component';
import { ManageQuestionsComponent } from './manage-questions/manage-questions.component';

const routes: Routes = [
  {
    path: '',
    component: AdminDashboardComponent, // 🏠 tableau de bord principal
    children: [
      { path: 'manage-users', component: ManageUsersComponent },
      { path: 'manage-quizzes', component: ManageQuizzesComponent },
      { path: 'manage-questions', component: ManageQuestionsComponent },
      { path: '', redirectTo: 'manage-quizzes', pathMatch: 'full' } // ✅ page par défaut
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
