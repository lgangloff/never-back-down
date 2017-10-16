import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { FilesComponent } from './files.component';
import { FileDetailComponent } from './file-detail/file-detail.component';
import { FileEditComponent } from './file-edit/file-edit.component';



const filesRoutes: Routes = [
  { path: 'files',  component: FilesComponent, canActivate: [AuthGuard] },
  { path: 'file/new', component: FileDetailComponent, canActivate: [AuthGuard] },
  { path: 'file/:id', component: FileDetailComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(filesRoutes),
    CommonModule,
    SharedModule,
  ],
  declarations: [FilesComponent, FileDetailComponent, FileEditComponent],
  exports: [FilesComponent, FileEditComponent, RouterModule],
  providers: []
})
export class FilesModule { }
