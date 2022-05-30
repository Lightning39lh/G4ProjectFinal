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
import { Ticket } from 'src/app/model/Ticket';
import { Exchange } from 'src/app/model/Exchange';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})

export class MyWalletComponent implements OnInit {
  crypto: any;
  wallet: Wallet = new Wallet(0, []);
  ticket: Ticket = new Ticket (0,"",0);
  exchange: Exchange = new Exchange(0,0,"",0,0,"");
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
  buyToken(ticket: Ticket){
    ticket.id_wallet=this.wallet.id;
    this.wS.buyToken(ticket).subscribe(data => {console.log("se agrego bien")})
  }
  sellToken(ticket: Ticket){
    ticket.id_wallet=this.wallet.id;
    this.wS.sellToken(ticket).subscribe(data => {console.log("quito bien")})
  }
  async exchangeToken(exchange: Exchange){
    exchange.id_wallet=this.wallet.id;
    this.aS.getToken(exchange.tokenName1).subscribe(data => {
      this.crypto = data;
      exchange.priceToken1 = this.crypto.ask;
    });
    this.aS.getToken(exchange.tokenName2).subscribe(data => {
      this.crypto = data;
      exchange.priceToken2 = this.crypto.ask;
    });
    await new Promise(f => setTimeout(f, 500));
    console.log(exchange);
    this.wS.exchangeToken(exchange).subscribe(data => {console.log("cambio bien")});
  }

}



