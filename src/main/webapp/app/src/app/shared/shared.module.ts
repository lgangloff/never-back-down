import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { RouterModule, Routes } from '@angular/router';


import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './login/logout.component';
import { AuthService } from './auth/auth.service';
import { AccountService } from './auth/account.service';
import { Principal } from './auth/principal.service';
import { AuthGuard, RoleManagerGuard, RoleAdminGuard } from './auth/auth.guard';
import { SubmitButtonComponent } from './submit-button/submit-button.component';
import { AlertErrorComponent } from './alert-error/alert-error.component';
import { AlertSuccessComponent } from './alert-success/alert-success.component';
import { I18nComponent } from './i18n/i18n.component';
import { TinyEditorComponent } from './tiny-editor/tiny-editor.component';

const loginRoutes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent },
];

@NgModule({
  imports: [
    RouterModule.forChild(loginRoutes),
    CommonModule, HttpModule, FormsModule, RouterModule
  ],
  exports: [
    CommonModule, HttpModule, FormsModule, RouterModule, SubmitButtonComponent, AlertErrorComponent, AlertSuccessComponent, I18nComponent, TinyEditorComponent
  ],
  providers:[
	  AuthGuard,
	  RoleManagerGuard,
	  RoleAdminGuard,
	  AccountService,
	  AuthService,
	  Principal,
  ],
  declarations: [LoginComponent, LogoutComponent, SubmitButtonComponent, AlertErrorComponent, AlertSuccessComponent, I18nComponent, TinyEditorComponent]
})
export class SharedModule { }
