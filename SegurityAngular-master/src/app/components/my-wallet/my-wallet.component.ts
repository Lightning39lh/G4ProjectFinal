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
import { Tokens } from 'src/app/model/Tokens';
import { AddTokensService } from 'src/app/services/add-tokens.service';

@Component({
  selector: 'app-my-wallet',
  templateUrl: './my-wallet.component.html',
  styleUrls: ['./my-wallet.component.css']
})

export class MyWalletComponent implements OnInit {

  crypto: any;
  tokensName: Tokens = new Tokens([]);
  wallet: Wallet = new Wallet(0, []);
  ticket: Ticket = new Ticket (0,"",0);
  constructor(private wS: WalletService, private aS: ApicryptoService, private aDS:AddTokensService, private ruta: Router) { }

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
    ticket.name_token= "USDT";
    ticket.id_wallet=1;
    this.wS.buyToken(ticket).subscribe(data => {console.log("se agrego bien")})
  }
  sellToken(ticket: Ticket){
    ticket.name_token= "USDT";
    ticket.id_wallet=1;
    console.log(ticket);
    this.wS.sellToken(ticket).subscribe(data => {console.log("quito bien")})
  }

  addToken(tokenName: string){
    //id wallet lo saco como antes
        this.aDS.addToken(tokenName).subscribe(data => {
          console.log("DATA:" + JSON.stringify(data));
          this.ruta.navigate(['/my-wallet'])}) 
      }
 
}



