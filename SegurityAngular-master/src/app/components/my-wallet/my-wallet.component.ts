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
  
  name : string = "BTC"
  precio: number=0;
  wallet: Wallet= new Wallet(0,[]);
  crypto:any;
  constructor(private wS:WalletService, private aS:ApicryptoService,private ruta:Router) { }

  async ngOnInit(): Promise<void> {
    
    (await this.wS.getWallet()).subscribe(data => {
  
      console.log(JSON.stringify(data));
      this.wallet=data;
      console.log(this.wallet);
      console.log("LO DE ARRIBA ES LA WALLET");
    })

    // this.aS.getToken(this.name).subscribe(data => {
    //   this.crypto = data;
    //   this.cantidad= this.crypto.ask;
    //   console.log(this.cantidad);
    //   console.log("Objeto API", this.crypto);
    // })
    /*for(this.i=0; this.i < this.wallet.token_wallet.length;this.i++ ){
      
      this.aS.getToken(t).subscribe(data => {
        this.crypto = data
    }*/
    await new Promise(f => setTimeout(f, 200));
    this.wallet.token_wallet.forEach(async (elemento, indice) => {
      console.log(elemento, indice);
      console.log(elemento.tokenName);
      console.log("TOKEN NAME")
      await new Promise(f => setTimeout(f, 200));
      this.aS.getToken(elemento.tokenName).subscribe(data => {
        this.crypto = data;
        elemento.valor_total= this.crypto.ask;

        console.log("precio del get token"+this.precio);
      })
      })
      await new Promise(f => setTimeout(f, 1000));
      this.wallet.token_wallet.forEach(async (elemento, indice) => {
      console.log(elemento.amount_tokens);
      console.log(elemento.valor_total);
      elemento.valor_total;
      elemento.valor_total= elemento.amount_tokens* elemento.valor_total ;
      console.log(elemento.valor_total);  })

}
 

  toAddTokens()
  {
      this.ruta.navigate(['addToken']);
  }

  search(){
    this.aS.getToken(this.name);
  }
}


