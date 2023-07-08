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
	DxAutocompleteModule,
	DxBoxModule,
	DxButtonModule,
	DxCheckBoxModule,
	DxContextMenuModule,
	DxDataGridModule,
	DxDateBoxModule,
	DxDropDownBoxModule,
	DxFormModule,
	DxListModule,
	DxLoadIndicatorModule,
	DxLoadPanelModule,
	DxMenuModule,
	DxNumberBoxModule,
	DxPopupModule,
	DxScrollViewModule,
	DxSelectBoxModule,
	DxTabPanelModule,
	DxTextAreaModule,
	DxTextBoxModule
} from 'devextreme-angular';
import {HospedeComponent} from './pages/hospede/hospede.component';
import {FormsModule} from "@angular/forms";
import {HospedeFormComponent} from "./shared/components/hospede-form/hospede-form.component";
import {ToolbarModule} from "./shared/components/toolbar/toolbar.component";
import {HotelComponent} from './pages/hotel/hotel.component';
import {CurrencyPipe, DatePipe, NgClass, NgForOf, NgIf} from "@angular/common";
import {QuartosComponent} from './pages/quartos/quartos.component';
import {QuartoFormComponent} from "./shared/components/quarto-form/quarto-form.component";
import {ReservaComponent} from './pages/reserva/reserva.component';
import {ReservaFormComponent} from "./shared/components/reserva-form/reserva-form.component";
import {ProdutoComponent} from './pages/produto/produto.component';
import {EntradaComponent} from './pages/entrada/entrada.component';
import {ConsumoComponent} from './pages/consumo/consumo.component';
import {NotaComponent} from './pages/nota/nota.component';
import {ProdutoFormComponent} from "./shared/components/produto-form/produto-form.component";
import {EntradaFormComponent} from "./shared/components/entrada-form/entrada-form.component";
import {ConsumoFormComponent, ConsumoFormModule} from "./shared/components/consumo-form/consumo-form.component";
import {NotaFormComponent, NotaFormModule} from "./shared/components/nota-form/nota-form.component";
import {EntradaEditFormComponent} from "./shared/components/entrada-edit-form/entrada-edit-form.component";

const routes: Routes = [
	{
		path: 'pages/notas',
		component: NotaComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/notas/cad',
		component: NotaFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/notas/edit/:id',
		component: NotaFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/consumo',
		component: ConsumoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/consumo/cad',
		component: ConsumoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/consumo/edit/:id',
		component: ConsumoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/entradas',
		component: EntradaComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/entradas/cad',
		component: EntradaFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/entradas/edit/:id',
		component: EntradaEditFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/produtos',
		component: ProdutoComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/produtos/cad',
		component: ProdutoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/produtos/edit/:id',
		component: ProdutoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/reservas',
		component: ReservaComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/reservas/cad',
		component: ReservaFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/reservas/edit/:id',
		component: ReservaFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/quartos',
		component: QuartosComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/quartos/cad',
		component: QuartoFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/quartos/edit/:id',
		component: QuartoFormComponent,
		canActivate: [AuthGuardService]
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
		path: 'pages/hospedes/edit/:id',
		component: HospedeFormComponent,
		canActivate: [AuthGuardService]
	},
	{
		path: 'pages/hotel',
		component: HotelComponent,
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
    imports: [RouterModule.forRoot(routes, {useHash: true}), DxDataGridModule, DxFormModule, DxLoadIndicatorModule, DxButtonModule, DxPopupModule, DxScrollViewModule, DxBoxModule, DxDateBoxModule, DxTextAreaModule, DxTextBoxModule, FormsModule, DxNumberBoxModule, DxMenuModule, ToolbarModule, NgIf, DxDropDownBoxModule, DxListModule, DxSelectBoxModule, DxLoadPanelModule, DxAutocompleteModule, DxTabPanelModule, DxCheckBoxModule, NgForOf, CurrencyPipe, DxContextMenuModule, NgClass, DatePipe, ConsumoFormModule, NotaFormModule],
	providers: [AuthGuardService],
	exports: [RouterModule],
	declarations: [
		HomeComponent,
		ProfileComponent,
		TasksComponent,
		HospedeComponent,
		HotelComponent,
		QuartosComponent,
		ReservaComponent,
		ProdutoComponent,
		EntradaComponent,
		ConsumoComponent,
		NotaComponent
	]
})
export class AppRoutingModule {
}
