import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {SideNavInnerToolbarModule, SideNavOuterToolbarModule, SingleCardModule} from './layouts';
import {
	ChangePasswordFormModule,
	CreateAccountFormModule,
	FooterModule,
	LoginFormModule,
	ResetPasswordFormModule
} from './shared/components';
import {AppInfoService, AuthService, ScreenService} from './shared/services';
import {UnauthenticatedContentModule} from './unauthenticated-content';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from "@angular/common/http";
import {HospedeFormModule} from './shared/components/hospede-form/hospede-form.component';
import {DevExtremeModule} from "devextreme-angular";
import {CommonModule} from "@angular/common";

@NgModule({
	declarations: [
		AppComponent,
	],
	imports: [
		BrowserModule,
		SideNavOuterToolbarModule,
		SideNavInnerToolbarModule,
		SingleCardModule,
		FooterModule,
		ResetPasswordFormModule,
		CreateAccountFormModule,
		ChangePasswordFormModule,
		LoginFormModule,
		UnauthenticatedContentModule,
		AppRoutingModule,
		HttpClientModule,
		HospedeFormModule,
		DevExtremeModule,
		CommonModule
	],
	providers: [
		AuthService,
		ScreenService,
		AppInfoService
	],
	exports: [
	],
	bootstrap: [AppComponent]
})
export class AppModule {
}
