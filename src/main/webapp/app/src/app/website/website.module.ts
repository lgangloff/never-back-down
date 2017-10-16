import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

import { AuthGuard } from '../shared/auth/auth.guard';
import { SharedModule } from '../shared/shared.module';
import { FilesModule } from '../files/files.module';
import { ConfigComponent } from './config/config.component';
import { WebSiteService } from './services/website.service';



const websiteRoutes: Routes = [
  { path: 'website/config',  component: ConfigComponent, canActivate: [AuthGuard] },
  //{ path: 'file/new', component: FileDetailComponent, canActivate: [AuthGuard] },
  //{ path: 'file/:id', component: FileDetailComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [
    RouterModule.forChild(websiteRoutes),
    CommonModule,
    SharedModule,
    FilesModule,
  ],
  declarations: [ConfigComponent],
  exports: [ConfigComponent, RouterModule],
  providers: [WebSiteService]
})
export class WebsiteModule { }
