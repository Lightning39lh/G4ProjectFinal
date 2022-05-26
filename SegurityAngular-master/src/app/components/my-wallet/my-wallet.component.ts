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
  
  name : string = "btc"
  cantidad : number = 0
  wallet: Wallet= new Wallet(0,[]);

  crypto: Crypto = new Crypto();
  constructor(private wS:WalletService, private aS:ApicryptoService,private ruta:Router) { }

  async ngOnInit(): Promise<void> {
    
    (await this.wS.getWallet()).subscribe(data => {
  
      console.log(JSON.stringify(data));
      this.wallet=data;
      console.log(this.wallet);
      console.log("LO DE ARRIBA ES LA WALLET");
    })

    this.aS.getToken(this.name).subscribe(data => {
      this.crypto = data
      console.log(data)
      console.log("Objeto API", this.crypto);
    })
  }

  toAddTokens()
  {
      this.ruta.navigate(['addToken']);
  }

  search(){
    this.aS.getToken(this.name);
  }
}


