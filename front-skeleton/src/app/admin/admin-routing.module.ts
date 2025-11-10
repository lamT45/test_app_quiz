import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ManageQuestionsComponent } from './manage-questions/manage-questions.component';
import { ManageQuizzesComponent } from './manage-quizzes/manage-quizzes.component';
import { ManageUsersComponent } from './manage-users/manage-users.component';


const routes: Routes = [
  { path: '', component: AdminDashboardComponent },
  { path: 'quizzes', component: ManageQuizzesComponent },
  { path: 'questions', component: ManageQuestionsComponent },
  { path: 'users', component: ManageUsersComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule {}
