import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard, RoleAdminGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { UsersComponent } from "./users.component";
import { UserDetailComponent } from './user-detail/user-detail.component';

const usersRoutes: Routes = [
  { path: 'users',  component: UsersComponent, canActivate: [AuthGuard, RoleAdminGuard] },
  { path: 'user/new', component: UserDetailComponent, canActivate: [AuthGuard, RoleAdminGuard] },
  { path: 'user/:id', component: UserDetailComponent, canActivate: [AuthGuard, RoleAdminGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(usersRoutes),
    CommonModule,
    SharedModule,
  ],
  declarations: [UsersComponent, UserDetailComponent],
  exports: [UsersComponent, RouterModule],
  providers: []
})
export class UsersModule { }
