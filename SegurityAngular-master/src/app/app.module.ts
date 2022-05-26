import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { MyWalletComponent } from './components/my-wallet/my-wallet.component';
import { InterceptorService } from './services/interceptor.service';
import { WalletService } from './services/wallet.service';
import { AddTokensComponent } from './components/add-tokens/add-tokens.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MyWalletComponent,
    AddTokensComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [WalletService,
  { provide: HTTP_INTERCEPTORS, useClass: InterceptorService, multi:true}
],
  bootstrap: [AppComponent]
})
export class AppModule { }
