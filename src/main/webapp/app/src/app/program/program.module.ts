import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { RoleManagerGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { FilesModule } from '../files/files.module';
import { ProgramComponent } from './program.component';
import { ProgramService } from './services/program.service';
import { ProgramDetailComponent } from './program-detail/program-detail.component';


const programRoutes: Routes = [
  { path: 'program',  component: ProgramComponent, canActivate: [RoleManagerGuard] },
  { path: 'program/new', component: ProgramDetailComponent, canActivate: [RoleManagerGuard] },
  { path: 'program/:id', component: ProgramDetailComponent, canActivate: [RoleManagerGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(programRoutes),
    CommonModule,
    SharedModule,
    FilesModule,
  ],
  declarations: [ProgramComponent, ProgramDetailComponent],
  providers: [ProgramService],
})
export class ProgramModule { }
