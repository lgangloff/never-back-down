import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { RoleManagerGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { CoachComponent } from './coach.component';
import { CoachService } from './services/coach.service';


const coachRoutes: Routes = [
  { path: 'coach',  component: CoachComponent, canActivate: [RoleManagerGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(coachRoutes),
    CommonModule,
    SharedModule,
  ],
  declarations: [CoachComponent],
  providers: [CoachService],
})
export class CoachModule { }
