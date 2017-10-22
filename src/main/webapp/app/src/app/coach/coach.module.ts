import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import {  } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { CoachComponent } from './coach.component';


const coachRoutes: Routes = [
  { path: 'files',  component: CoachComponent, canActivate: [RoleManagerGuard] },
];

@NgModule({
  imports: [
    CommonModule
  ],
  declarations: [CoachComponent]
})
export class CoachModule { }
