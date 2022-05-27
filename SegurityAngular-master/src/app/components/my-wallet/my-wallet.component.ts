import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { interval } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import { Cuenta } from 'src/app/model/Cuenta';
import { Wallet } from 'src/app/model/Wallet';
import { Crypto } from 'src/app/model/Crypto';
import { WalletService } from 'src/app/services/wallet.service';
import { ApicryptoService } from 'src/app/services/apicrypto.service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})

export class MyWalletComponent implements OnInit {

  crypto: any;
  wallet: Wallet = new Wallet(0, []);
  constructor(private wS: WalletService, private aS: ApicryptoService, private ruta: Router) { }

  ngOnInit(): void{

    this.getSessionWallet();
    this.getTokens();
  }

  async getSessionWallet() {
    (await this.wS.getWallet()).subscribe(data => {
      console.log(JSON.stringify(data));
      this.wallet = data;
    })
  }

  async getTokens() {
    await new Promise(f => setTimeout(f, 100));
    this.wallet.token_wallet.forEach(async (elemento, indice) => {
        this.aS.getToken(elemento.tokenName).subscribe(data => {
        this.crypto = data;
        elemento.valor_total = this.crypto.ask;
        elemento.valor_total *= elemento.amount_tokens;
      })
    })
  }
 
  toAddTokens() {
    this.ruta.navigate(['addToken']);
  }
 
}



