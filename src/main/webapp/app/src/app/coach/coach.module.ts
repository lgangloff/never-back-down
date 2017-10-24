import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { RoleManagerGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { FilesModule } from '../files/files.module';
import { CoachComponent } from './coach.component';
import { CoachService } from './services/coach.service';
import { CoachDetailComponent } from './coach-detail/coach-detail.component';


const coachRoutes: Routes = [
  { path: 'coach',  component: CoachComponent, canActivate: [RoleManagerGuard] },
  { path: 'coach/new', component: CoachDetailComponent, canActivate: [RoleManagerGuard] },
  { path: 'coach/:id', component: CoachDetailComponent, canActivate: [RoleManagerGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(coachRoutes),
    CommonModule,
    SharedModule,
    FilesModule,
  ],
  declarations: [CoachComponent, CoachDetailComponent],
  providers: [CoachService],
})
export class CoachModule { }
