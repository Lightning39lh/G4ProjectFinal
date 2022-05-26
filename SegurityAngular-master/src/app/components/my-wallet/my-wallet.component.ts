import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cuenta } from 'src/app/model/Cuenta';
import { Wallet } from 'src/app/model/Wallet';
import { Crypto } from 'src/app/model/Crypto';
import { WalletService } from 'src/app/services/wallet.service';
import { ApicryptoService } from 'src/app/services/apicrypto.service';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})

export class MyWalletComponent implements OnInit {

  price: number = 0;
  wallet: Wallet = new Wallet(0, []);
  crypto: any;
  constructor(private wS: WalletService, private aS: ApicryptoService, private ruta: Router) { }

  ngOnInit(): void {

    this.getWallet()
    this.wait()
    this.getTokens() 

  }

  async getWallet() {
    (await this.wS.getWallet()).subscribe(data => {
      console.log(JSON.stringify(data));
      this.wallet = data;
      console.log(this.wallet);
      console.log("LO DE ARRIBA ES LA WALLET");
    })
  }

  async getTokens() {
    console.log("entre")
    this.wallet.token_wallet.forEach(async (elemento, indice) => {
      console.log("asdasd")
        this.aS.getToken(elemento.tokenName).subscribe(data => {
        this.crypto = data;
        elemento.valor_total = this.crypto.ask;
        console.log("valor total", elemento.valor_total);
        this.wallet.token_wallet.forEach(async (elemento, indice) => {
          elemento.valor_total = elemento.amount_tokens * elemento.valor_total;
        });
      })
    })
  }

  async wait(){
    await new Promise(f => setTimeout(f, 5000))
  }

  toAddTokens() {
    this.ruta.navigate(['addToken']);
  }

}



