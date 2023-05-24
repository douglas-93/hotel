import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {
	ChangePasswordFormComponent,
	CreateAccountFormComponent,
	LoginFormComponent,
	ResetPasswordFormComponent
} from './shared/components';
import {AuthGuardService} from './shared/services';
import {HomeComponent} from './pages/home/home.component';
import {ProfileComponent} from './pages/profile/profile.component';
import {TasksComponent} from './pages/tasks/tasks.component';
import {
	DxBoxModule,
	DxButtonModule,
	DxDataGridModule,
	DxDateBoxModule, DxDropDownBoxModule,
	DxFormModule, DxListModule,
	DxLoadIndicatorModule, DxLoadPanelModule, DxMenuModule, DxNumberBoxModule,
	DxPopupModule,
	DxScrollViewModule, DxSelectBoxModule,
	DxTextAreaModule,
	DxTextBoxModule
} from 'devextreme-angular';
import {HospedeComponent} from './pages/hospede/hospede.component';
import {FormsModule} from "@angular/forms";
import {HospedeFormComponent} from "./shared/components/hospede-form/hospede-form.component";
import {ToolbarModule} from "./shared/components/toolbar/toolbar.component";
import { HotelComponent } from './pages/hotel/hotel.component';
import {NgIf} from "@angular/common";

const routes: Routes = [
  {
    path: 'pages/hotel',
    component: HotelComponent,
    canActivate: [ AuthGuardService ]
  },
	{
		path: 'pages/hospedes',
		component: HospedeComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/hospedes/cad',
		component: HospedeFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/hospedes/cad/:id',
		component: HospedeFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'tasks',
		component: TasksComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'profile',
		component: ProfileComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'home',
		component: HomeComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'login-form',
		component: LoginFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'reset-password',
		component: ResetPasswordFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'create-account',
		component: CreateAccountFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'change-password/:recoveryCode',
		component: ChangePasswordFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: '**',
		redirectTo: 'home'
	}
];

@NgModule({
	imports: [RouterModule.forRoot(routes, {useHash: true}), DxDataGridModule, DxFormModule, DxLoadIndicatorModule, DxButtonModule, DxPopupModule, DxScrollViewModule, DxBoxModule, DxDateBoxModule, DxTextAreaModule, DxTextBoxModule, FormsModule, DxNumberBoxModule, DxMenuModule, ToolbarModule, NgIf, DxDropDownBoxModule, DxListModule, DxSelectBoxModule, DxLoadPanelModule],
	providers: [AuthGuardService],
	exports: [RouterModule],
	declarations: [
		HomeComponent,
		ProfileComponent,
		TasksComponent,
		HospedeComponent,
  HotelComponent
	]
})
export class AppRoutingModule {
}
