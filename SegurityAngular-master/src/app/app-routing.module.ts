import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddTokensComponent } from './components/add-tokens/add-tokens.component';
import { LoginComponent } from './components/login/login.component';
import { MyWalletComponent } from './components/my-wallet/my-wallet.component';
import { GuardGuard } from './services/guard.guard';

const routes: Routes = [
  {path:'login',component:LoginComponent},
  {path:'my-wallet',component:MyWalletComponent,canActivate:[GuardGuard]},
  {path:'addToken',component:AddTokensComponent,canActivate:[GuardGuard]},
  {path:'',redirectTo:'login',pathMatch:'full'}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
